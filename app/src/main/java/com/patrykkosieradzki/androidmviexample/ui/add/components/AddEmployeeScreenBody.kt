package com.patrykkosieradzki.androidmviexample.ui.add.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract

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
                eventHandler.invoke(AddEmployeeContract.Event.UpdateFormEvent(firstName = it))
            }
        )
        OutlinedTextField(
            label = stringResource(id = R.string.last_name),
            value = state.lastName,
            onChange = {
                eventHandler.invoke(AddEmployeeContract.Event.UpdateFormEvent(lastName = it))
            }
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
                        eventHandler.invoke(AddEmployeeContract.Event.UpdateFormEvent(address = it))
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
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            modifier = Modifier.heightIn(max = 200.dp)
        ) {
            itemsIndexed(state.addresses) { index, item ->
                EmployeeAddressListItem(
                    name = item.name,
                    onRemoveClicked = {
                        eventHandler.invoke(AddEmployeeContract.Event.RemoveAddressEvent(item))
                    }
                )
                if (index < state.addresses.size - 1)
                    Divider(color = Color.Transparent, thickness = 10.dp)
            }
        }
    }
}

@Composable
fun EmployeeAddressListItem(
    name: String,
    onRemoveClicked: () -> Unit
) {
    Card(
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = name)
            Button(
                onClick = onRemoveClicked,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.remove))
            }
        }
    }
}

@Composable
fun OutlinedTextField(
    label: String = "",
    value: String = "",
    onChange: (String) -> Unit
) {
    androidx.compose.material.OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onChange,
        label = { Text(text = label) }
    )
}