package com.bsa.currencyconverter.data.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest.json")
    suspend fun getRates(
            @Query("base")base:String,
            @Query("app_id")appId:String
    ):Response<CurrencyResponse>

}