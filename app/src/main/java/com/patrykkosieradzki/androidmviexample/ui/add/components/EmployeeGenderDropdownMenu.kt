package com.patrykkosieradzki.androidmviexample.ui.add.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract

@Composable
fun EmployeeGenderDropdownMenu(
    genders: List<Gender>,
    eventHandler: (AddEmployeeContract.Event) -> Unit
) {
    var isDropdownMenuExpanded by remember { mutableStateOf(false) }

    DropdownMenu(
        expanded = isDropdownMenuExpanded,
        onDismissRequest = { isDropdownMenuExpanded = false }
    ) {
        genders.forEach { gender ->
            DropdownMenuItem(onClick = {
                isDropdownMenuExpanded = false
                eventHandler.invoke(AddEmployeeContract.Event.UpdateFormEvent(gender = gender.name))
            }) {
                Text(gender.name)
            }
        }
    }
}