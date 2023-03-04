package com.example.proyecto_divisa.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://v6.exchangerate-api.com/v6/ebf3ae0c3d9a5670e4381e6d/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface ExchangeRateApiService {
    @GET("latest/{base}")
    suspend fun getExchangeRates(@Path("base") base: String): ExchangeRates
}

/*Objeto al cual se puede acceder desde el resto de la APP*/
    val exchangeRateApiService : ExchangeRateApiService by lazy {
        retrofit.create(ExchangeRateApiService::class.java)}



