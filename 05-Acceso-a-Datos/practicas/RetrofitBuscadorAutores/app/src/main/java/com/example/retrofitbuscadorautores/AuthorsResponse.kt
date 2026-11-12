package com.example.retrofitbuscadorautores

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.google.gson.annotations.SerializedName

    data class AuthorsResponse(
        var numFound: Int,
        @SerializedName("docs") var autores: List<Autores>
    )
@Entity
data class Autores(
    @PrimaryKey var key: String,
    var birth_date: String?,
    var name: String
)