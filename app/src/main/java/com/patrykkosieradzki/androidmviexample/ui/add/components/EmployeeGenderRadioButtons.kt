package com.patrykkosieradzki.androidmviexample.ui.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.patrykkosieradzki.androidmviexample.R
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.Event.UpdateFormEvent

@Preview
@Composable
fun EmployeeGenderRadioButtons(
    currentGender: String = "",
    genders: List<Gender> = emptyList(),
    eventHandler: (AddEmployeeContract.Event) -> Unit = {}
) {
    fun onClick(genderName: String) {
        eventHandler.invoke(UpdateFormEvent(gender = genderName))
    }

    Column(
        modifier = Modifier.padding(top = 15.dp)
    ) {
        Text(text = stringResource(id = R.string.select_gender))
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            genders.forEach { gender ->
                Row(modifier = Modifier.clickable { onClick(gender.name) }) {
                    RadioButton(
                        selected = gender.name == currentGender,
                        onClick = { onClick(gender.name) }
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = gender.name
                    )
                }
            }
        }
    }
}
