package com.moin.currency_converter.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun DropDownView(
    modifier: Modifier,
    expanded: Boolean,
    listItems: List<String>,
    selectedItem: String,
    onItemSelected: (picked: String) -> Unit
)