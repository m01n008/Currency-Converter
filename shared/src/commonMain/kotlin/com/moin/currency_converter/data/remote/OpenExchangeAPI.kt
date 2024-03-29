package com.moin.currency_converter.data.remote

import com.moin.currency_converter.data.Currency
import kotlinx.serialization.json.JsonObject

interface OpenExchangeAPI {
    suspend fun getCurrencies(): JsonObject
    suspend fun getLatestRates(baseCurrency: String): JsonObject
    suspend fun getHistoricalRates(): JsonObject

}