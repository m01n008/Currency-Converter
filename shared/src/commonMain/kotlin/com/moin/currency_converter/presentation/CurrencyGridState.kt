package com.moin.currency_converter.presentation

import com.moin.currency_converter.data.ConvertedCurrency

sealed interface CurrencyGridState{
    object Loading: CurrencyGridState
    data class Error(val message: String): CurrencyGridState
    data class Success(val list: List<ConvertedCurrency>): CurrencyGridState
}