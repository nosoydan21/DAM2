package com.dam.modelodedatos.autores

data class AutorBusquedaResponse(
    val docs: List<AuthorDoc>
)

data class AuthorDoc(
    val name:String,
    var birth_date:String?
)

