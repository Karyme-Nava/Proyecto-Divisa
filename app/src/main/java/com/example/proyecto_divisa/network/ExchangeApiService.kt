package com.example.proyecto_divisa.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://v6.exchangerate-api.com/v6/ebf3ae0c3d9a5670e4381e6d/latest"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ExchangeApiService {
    @GET("USD")
    suspend fun getPhotos():String
}

/*Objeto al cual se puede acceder desde el resto de la APP*/
object ExchangeApi {
    val retrofitService : ExchangeApiService by lazy {
        retrofit.create(ExchangeApiService::class.java)
    }
}