package com.patrykkosieradzki.androidmviexample.ui.add

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.*
import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.model.Employee
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.domain.usecases.GetGendersUseCase
import com.patrykkosieradzki.androidmviexample.domain.usecases.SaveEmployeeUseCase
import com.patrykkosieradzki.androidmviexample.utils.BaseJunit4Test
import com.patrykkosieradzki.androidmviexample.utils.UiState
import com.patrykkosieradzki.androidmviexample.utils.successData
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class AddEmployeeViewModelTest : BaseJunit4Test() {

    private val getGendersUseCase: GetGendersUseCase = mock {
        onBlocking { invoke() } doReturn GENDERS
    }

    @Mock
    private lateinit var saveEmployeeUseCase: SaveEmployeeUseCase

    private lateinit var viewModel: AddEmployeeViewModel

    @Before
    fun setUp() = runBlocking {
        viewModel = AddEmployeeViewModel(
            getGendersUseCase = getGendersUseCase,
            saveEmployeeUseCase = saveEmployeeUseCase
        )
    }

    @Test
    fun `on init should load genders`() = runBlockingTest {
        verify(getGendersUseCase, times(1)).invoke()
        assertThat(viewModel.currentState.successData.genders).isEqualTo(GENDERS)
    }

    @Test
    fun `on UpdateFormEvent should update form`() {
        val firstName = "Alex"
        val lastName = "Tikitaka"
        viewModel.handleEvent(
            AddEmployeeContract.Event.UpdateFormEvent(
                firstName = firstName,
                lastName = lastName
            )
        )

        assertThat(viewModel.currentState.successData.firstName).isEqualTo(firstName)
        assertThat(viewModel.currentState.successData.lastName).isEqualTo(lastName)
    }

    @Test
    fun `on AddAddressEvent should add address`() {
        val streetName = "SomeStreet"
        viewModel.handleEvent(AddEmployeeContract.Event.UpdateFormEvent(address = streetName))
        viewModel.handleEvent(AddEmployeeContract.Event.AddAddressEvent)

        assertThat(viewModel.currentState.successData.address).isEmpty()
        assertThat(viewModel.currentState.successData.addresses).isEqualTo(
            listOf(
                Address(name = streetName)
            )
        )
    }

    @Test
    fun `on RemoveAddressEvent should remove address`() {
        val address = Address(name = "SomeStreet 1")
        val addressToBeRemoved = Address(name = "SomeStreet 2")
        viewModel.setUiState {
            UiState.Success(
                AddEmployeeContract.State(
                    addresses = listOf(
                        address,
                        addressToBeRemoved
                    )
                )
            )
        }

        viewModel.handleEvent(AddEmployeeContract.Event.RemoveAddressEvent(addressToBeRemoved))

        assertThat(viewModel.currentState.successData.addresses).hasSize(1)
        assertThat(viewModel.currentState.successData.addresses[0]).isEqualTo(address)
    }

    @Test
    fun `on SaveEmployeeEvent when form is valid should save employee`() = runBlockingTest {
        val employeeAddresses = listOf(Address(name = "SomeStreet 1"))
        viewModel.setUiState {
            UiState.Success(
                AddEmployeeContract.State(
                    firstName = "Adam",
                    lastName = "Kowalski",
                    gender = "Male",
                    addresses = employeeAddresses
                )
            )
        }

        viewModel.handleEvent(AddEmployeeContract.Event.SaveEmployeeEvent)

        val argumentCaptor = argumentCaptor<Employee>()
        verify(saveEmployeeUseCase, times(1)).invoke(argumentCaptor.capture())

        val employee = argumentCaptor.firstValue
        assertThat(employee.firstName).isEqualTo("Adam")
        assertThat(employee.lastName).isEqualTo("Kowalski")
        assertThat(employee.gender).isEqualTo("Male")
        assertThat(employee.addresses).isEqualTo(employeeAddresses)
    }

    @Test
    fun `on SaveEmployeeEvent when form is valid should show snackbar and clear form`() = runBlockingTest {
        val employeeAddresses = listOf(Address(name = "SomeStreet 1"))
        viewModel.setUiState {
            UiState.Success(
                AddEmployeeContract.State(
                    firstName = "Adam",
                    lastName = "Kowalski",
                    gender = "Male",
                    addresses = employeeAddresses
                )
            )
        }

        viewModel.handleEvent(AddEmployeeContract.Event.SaveEmployeeEvent)

        with(viewModel) {
            with(currentSnackbarState) {
                assertThat(isShown).isTrue()
                assertThat(message).isEqualTo("New employee saved! :)")
            }
            with(currentState.successData) {
                assertThat(firstName).isEmpty()
                assertThat(lastName).isEmpty()
                assertThat(gender).isEmpty()
                assertThat(address).isEmpty()
                assertThat(addresses).isEmpty()
            }
        }
    }

    @Test
    fun `on SaveEmployeeEvent when form is not valid should show error snackbar`() =
        runBlockingTest {
            viewModel.setUiState {
                UiState.Success(
                    AddEmployeeContract.State(
                        firstName = "Adam",
                        lastName = "Kowalski",
                        gender = "",
                        addresses = emptyList()
                    )
                )
            }

            viewModel.handleEvent(AddEmployeeContract.Event.SaveEmployeeEvent)

            assertThat(viewModel.currentSnackbarState.isShown).isTrue()
            assertThat(viewModel.currentSnackbarState.message).isEqualTo("Form is not valid")
        }

    companion object {
        val GENDERS = listOf(
            Gender(id = 1, name = "Male"),
            Gender(id = 2, name = "Female")
        )
    }
}