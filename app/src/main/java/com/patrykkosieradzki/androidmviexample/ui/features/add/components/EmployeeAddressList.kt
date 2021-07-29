package com.patrykkosieradzki.androidmviexample.ui.features.add.components

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
import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.ui.features.add.AddEmployeeContract

@Composable
fun EmployeeAddressList(
    addresses: List<Address>,
    eventHandler: (AddEmployeeContract.Event) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.heightIn(max = 200.dp)
    ) {
        itemsIndexed(addresses) { index, item ->
            EmployeeAddressListItem(
                name = item.name,
                onRemoveClicked = {
                    eventHandler.invoke(AddEmployeeContract.Event.RemoveAddressEvent(item))
                }
            )
            if (index < addresses.size - 1)
                Divider(color = Color.Transparent, thickness = 10.dp)
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

