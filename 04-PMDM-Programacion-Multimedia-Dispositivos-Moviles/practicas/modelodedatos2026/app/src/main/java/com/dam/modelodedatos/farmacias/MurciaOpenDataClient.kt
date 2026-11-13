package com.dam.modelodedatos.farmacias

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MurciaOpenDataClient {
    private const val BASE_URL = "https://datosabiertos.regiondemurcia.es/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MurciaOpenDataService = retrofit.create(MurciaOpenDataService::class.java)
}
