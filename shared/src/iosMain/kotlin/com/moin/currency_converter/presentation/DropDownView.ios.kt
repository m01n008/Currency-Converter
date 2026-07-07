package com.moin.currency_converter.presentation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Popup
import com.moin.currency_converter.style.Palette


@Composable
actual fun DropDownView(
    modifier: Modifier,
    expanded: Boolean,
    listItems: List<String>,
    selectedItem: String,
    onItemSelected: (picked: String) -> Unit
) {
    var isDropDownExpanded  by remember { mutableStateOf(expanded)}
    var mutableSelectedItem by remember { mutableStateOf(selectedItem) }
    var dropDownViewSize by remember{ mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier.onGloballyPositioned { layoutCoordinates ->
            dropDownViewSize = layoutCoordinates.size
        }
    ) {

        ClickableText(
            text = buildAnnotatedString {
                    withStyle(SpanStyle(color = Color.Black)) {
                        append(mutableSelectedItem)
                    }
            },
            onClick = {
                isDropDownExpanded = !isDropDownExpanded
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Icon(
            imageVector = if (isDropDownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "drop down icon",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        )
    }
    DropdownMenuList(
        options = listItems,
        onSelectedAction = { option ->
            mutableSelectedItem = option
            isDropDownExpanded = false
            onItemSelected(option)
        },
        isDropDownExpanded = isDropDownExpanded,
        onDismissRequest = {isDropDownExpanded = false},
        dropDownViewSize

    )
}

@Composable
fun DropdownMenuList(
    options: List<String>,
    onSelectedAction: (String) -> Unit,
    isDropDownExpanded: Boolean,
    onDismissRequest: () -> Unit,
    dropDownViewSize: IntSize
) {
    if (isDropDownExpanded) {
      Popup(alignment = Alignment.TopStart,
            onDismissRequest = onDismissRequest,
            focusable = true) {
          Surface(
              shape = RoundedCornerShape(8.dp),
              elevation = 2.dp,
              modifier = Modifier
                  .clip(shape = RoundedCornerShape(16.dp))
                  .width(150.dp)
                  .height(400.dp)
                  .padding(16.dp)
                  .border(
                      width = 2.dp,
                      color = Palette.LightBlue,
                      shape = RoundedCornerShape(8.dp)
                  )

          ) {
              LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                  items(options) { option ->
                      DropdownMenuListRow(
                          option = option,
                          onSelectedAction = onSelectedAction
                      )
                  }
              }
          }
      }
    }
}

@Composable
fun DropdownMenuListRow(
    option: String,
    onSelectedAction: (String) -> Unit
) {
    ClickableText(
        text = AnnotatedString(option.trim()),
        onClick = { offset ->
            onSelectedAction(option)
        },
        modifier = Modifier
            .padding(vertical = 4.dp)
            .wrapContentSize()
            .padding(horizontal = 4.dp)
            .background(Color.White),
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Stable
data class DropdownMenuOption(val option: String)

@Composable
fun PreviewDropdownMenu() {
   var listItems = ArrayList<String>()
    for (i in 0..100){
        listItems.add("items $i")
    }

    val selectedOption = remember { mutableStateOf<DropdownMenuOption?>(null) }

    DropDownView(
        modifier = Modifier.fillMaxSize().width(40.dp),
        listItems = listItems,
        selectedItem = "AED",
        expanded = false,
        onItemSelected = {}
    )
}





