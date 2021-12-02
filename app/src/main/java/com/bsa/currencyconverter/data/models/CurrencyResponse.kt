package com.bsa.currencyconverter.data.models

data class CurrencyResponse(
    val base: String,
    val rates: Rates
)