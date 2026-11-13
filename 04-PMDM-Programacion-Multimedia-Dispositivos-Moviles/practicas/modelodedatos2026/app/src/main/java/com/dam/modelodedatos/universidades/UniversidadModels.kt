package com.dam.modelodedatos.universidades

import com.google.gson.annotations.SerializedName

data class University(
    val name: String,
    @SerializedName("web_pages")
    val webPages: List<String>
)
