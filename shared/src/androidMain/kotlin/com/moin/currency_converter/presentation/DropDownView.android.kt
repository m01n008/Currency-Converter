package com.moin.currency_converter.presentation

import android.widget.Toast
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
actual fun DropDownView(
    modifier: Modifier,
    expanded: Boolean,
    listItems: List<String>,
    selectedItem: String,
    onItemSelected: (picked: String) -> Unit
) {
    val contextForToast = LocalContext.current
    var mutableExpanded  by remember { mutableStateOf(expanded) }
    var mutableSelectedItem by remember { mutableStateOf(selectedItem) }
    // box
    ExposedDropdownMenuBox(
        expanded = mutableExpanded,
        onExpandedChange = {
            mutableExpanded = !mutableExpanded
        }
    ) {
        // text field
        TextField(
            value = mutableSelectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "currency")},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = mutableExpanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = mutableExpanded,
            onDismissRequest = { mutableExpanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    mutableSelectedItem = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    mutableExpanded = false
                    onItemSelected(selectedOption)
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}
