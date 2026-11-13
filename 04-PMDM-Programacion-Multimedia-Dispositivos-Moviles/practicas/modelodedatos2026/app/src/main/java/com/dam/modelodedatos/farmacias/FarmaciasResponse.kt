package com.dam.modelodedatos.farmacias

import com.google.gson.annotations.SerializedName

data class FarmaciasResponse(
    val success: Boolean,
    val result: FarmaciasResult
)

data class FarmaciasResult(
    val sql:String,
    records: List<Farmacia>
)

data class Farmacia(
    @SerializedName("Nombre") val nombre:String,
    @SerializedName("Municipio") val municipio:String,
)

