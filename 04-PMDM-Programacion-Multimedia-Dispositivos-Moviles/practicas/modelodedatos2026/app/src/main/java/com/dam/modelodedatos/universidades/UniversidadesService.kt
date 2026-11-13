package com.dam.modelodedatos.universidades

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversidadesService {

    @GET("seach")
    suspend fun searchUniversities(
        @Query("country") country: String,
        @Query("name") name: String
    ): Response<List<University>>
}
