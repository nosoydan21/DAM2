package com.dam.modelodedatos.autores

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AutorClient {
    private const val BASE_URL = "https://openlibrary.org/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: AutorService = retrofit.create(AutorService::class.java)
}
