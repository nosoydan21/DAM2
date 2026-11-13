package com.dam.modelodedatos.farmacias

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.SQLData

interface MurciaOpenDataService {
    @GET("")
    suspend fun searchFarmacias(@Query("sql")sql:String): Response<FarmaciasResponse>
}
