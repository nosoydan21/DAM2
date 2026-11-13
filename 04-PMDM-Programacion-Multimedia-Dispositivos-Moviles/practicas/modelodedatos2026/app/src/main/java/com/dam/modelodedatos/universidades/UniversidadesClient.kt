package com.dam.modelodedatos.universidades

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UniversidadesClient {
    private const val BASE_URL = "http://universities.hipolabs.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: UniversidadesService = retrofit.create(UniversidadesService::class.java)
}
