package com.dam.modelodedatos.autores

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutorService {

    @GET("search/authors.json")
    suspend fun searchAuthor(@Query("q") query: String): Response<AutorBusquedaResponse>
}
