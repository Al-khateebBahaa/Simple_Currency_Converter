package com.bsa.currencyconverter.main

import com.bsa.currencyconverter.data.models.CurrencyResponse
import com.bsa.currencyconverter.util.Resource

interface MainRepository {

    suspend fun getRates(base:String):Resource<CurrencyResponse>
}