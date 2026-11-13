package com.dam.modelodedatos.universidades.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.R
import com.dam.modelodedatos.universidades.University

class UniversidadAdapter(private val universities: List<University>) : RecyclerView.Adapter<UniversidadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversidadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fila_universidades, parent, false)
        return UniversidadViewHolder(view)
    }

    override fun onBindViewHolder(holder: UniversidadViewHolder, position: Int) {
        val university = universities[position]
        holder.tvName.text = university.name
        holder.tvWeb.text = university.webPages.firstOrNull() ?: "No web page"
    }

    override fun getItemCount() = universities.size
}