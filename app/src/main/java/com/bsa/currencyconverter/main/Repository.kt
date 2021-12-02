package com.bsa.currencyconverter.main

import com.bsa.currencyconverter.data.models.CurrencyApi
import com.bsa.currencyconverter.data.models.CurrencyResponse
import com.bsa.currencyconverter.util.Constance.APP_ID
import com.bsa.currencyconverter.util.Resource
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {


    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base, APP_ID)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)

            } else {
                Resource.Error(response.message())
            }

        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "An error occurred")
        }
    }
}