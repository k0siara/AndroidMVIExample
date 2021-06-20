package com.patrykkosieradzki.androidmviexample.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun AddEmployeeScreen(
    viewModel: AddEmployeeViewModel = getViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    label = "First name",
                    value = state.value.firstName,
                    onChange = { viewModel.updateForm(firstName = it) }
                )
                OutlinedTextField(
                    label = "Last name",
                    value = state.value.lastName,
                    onChange = { viewModel.updateForm(lastName = it) }
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        Modifier.weight(2f)
                    ) {
                        OutlinedTextField(
                            label = "Address",
                            value = state.value.address,
                            onChange = { viewModel.updateForm(address = it) }
                        )
                    }
                    Spacer(Modifier.size(10.dp))
                    Box(
                        Modifier.weight(1f)
                    ) {
                        Button(
                            onClick = { viewModel.onAddAddressClicked() },
                        ) {
                            Text(text = "Add address")
                        }
                    }
                }
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(state.value.addresses) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                                .fillMaxWidth(),
                            elevation = 2.dp,
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(corner = CornerSize(16.dp))
                        ) {
                            Text(text = it.name)
                        }
                    }
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = "Save employee")
                }
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
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onChange,
        label = { Text(text = label) }
    )
}
