package com.patrykkosieradzki.androidmviexample.ui.add.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.Event.UpdateFormEvent

@Composable
fun AddEmployeeScreenBody(
    state: AddEmployeeContract.State,
    eventHandler: (AddEmployeeContract.Event) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AddEmployeeForm(
                state = state,
                eventHandler = eventHandler
            )
            Button(
                onClick = {
                    eventHandler.invoke(AddEmployeeContract.Event.SaveEmployeeEvent)
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.save_employee))
            }
        }
    }
}

@Composable
fun AddEmployeeForm(
    state: AddEmployeeContract.State,
    eventHandler: (AddEmployeeContract.Event) -> Unit
) {
    Column {
        OutlinedTextField(
            label = stringResource(id = R.string.first_name),
            value = state.firstName,
            onChange = {
                eventHandler.invoke(UpdateFormEvent(firstName = it))
            }
        )
        OutlinedTextField(
            label = stringResource(id = R.string.last_name),
            value = state.lastName,
            onChange = {
                eventHandler.invoke(UpdateFormEvent(lastName = it))
            }
        )
        EmployeeGenderRadioButtons(
            currentGender = state.gender,
            genders = state.genders,
            eventHandler = eventHandler
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier.weight(2f)
            ) {
                OutlinedTextField(
                    label = stringResource(id = R.string.address),
                    value = state.address,
                    onChange = {
                        eventHandler.invoke(UpdateFormEvent(address = it))
                    }
                )
            }
            Spacer(Modifier.size(10.dp))
            Box(
                Modifier.weight(1f)
            ) {
                Button(
                    onClick = {
                        eventHandler.invoke(AddEmployeeContract.Event.AddAddressEvent)
                    },
                ) {
                    Text(text = stringResource(id = R.string.add_address))
                }
            }
        }
        EmployeeAddressList(
            addresses = state.addresses,
            eventHandler = eventHandler
        )
    }
}

@Composable
fun OutlinedTextField(
    label: String = "",
    value: String = "",
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onChange,
        label = { Text(text = label) }
    )
}
