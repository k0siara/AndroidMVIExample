package com.patrykkosieradzki.androidmviexample.ui.employees

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.patrykkosieradzki.androidmviexample.storage.dao.EmployeeDao
import com.patrykkosieradzki.androidmviexample.storage.model.EmployeeWithGenderAndAddresses
import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel
import kotlinx.coroutines.flow.Flow

class EmployeeListViewModel(
    private val employeeDao: EmployeeDao
) :
    BaseViewModel<EmployeeListContract.State, EmployeeListContract.Event, EmployeeListContract.Effect>(
        initialState = EmployeeListContract.State.Success()
    ) {

    val employees: Flow<PagingData<EmployeeWithGenderAndAddresses>> = Pager(
        PagingConfig(
            enablePlaceholders = true,
            pageSize = EMPLOYEES_PAGE_SIZE,
            initialLoadSize = EMPLOYEES_PAGE_SIZE,
            prefetchDistance = EMPLOYEES_PAGE_SIZE,
        ),
        pagingSourceFactory = { employeeDao.pagingSource() }
    ).flow.cachedIn(viewModelScope)

    override fun handleEvent(event: EmployeeListContract.Event) {}

    fun setLoadingState() {
        updateUiState { EmployeeListContract.State.Loading() }
    }

    fun setSuccessState() {
        updateUiState { EmployeeListContract.State.Success() }
    }

    fun setEmptyState() {
        updateUiState { EmployeeListContract.State.Empty() }
    }

    fun setErrorState() {
        updateUiState { EmployeeListContract.State.Error() }
    }

    fun onAddEmployeeButtonClicked() {
        navigateTo(EmployeeListFragmentDirections.toAddEmployeeFragment())
    }

    companion object {
        const val EMPLOYEES_PAGE_SIZE = 10
    }
}
