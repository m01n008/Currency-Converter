package com.moin.currency_converter

import androidx.compose.ui.graphics.Color
import com.moin.currency_converter.data.ConvertedCurrency
import com.moin.currency_converter.data.Currency



internal fun getRandomColor(): Color {
return Color.Unspecified
}
internal fun getFilteredCurrencyList(list: List<ConvertedCurrency>, value: String): List<ConvertedCurrency> {
   return list.filter { it.code != value }
}