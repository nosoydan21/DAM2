package com.dam.modelodedatos.autores.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.R
import com.dam.modelodedatos.autores.AuthorDoc

class AutorAdaptador(private val autores: List<AuthorDoc>) : RecyclerView.Adapter<AutorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fila_autor, parent, false)
        return AutorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AutorViewHolder, position: Int) {
        val autor = autores[position]
        holder.tvName.text = autor.name
        holder.tvBirthDate.text = autor.birth_date ?: "sin datos"
    }

    override fun getItemCount() = autores.size
}