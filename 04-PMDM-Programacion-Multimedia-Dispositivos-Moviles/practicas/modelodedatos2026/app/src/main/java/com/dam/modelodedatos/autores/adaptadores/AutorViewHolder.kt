package com.dam.modelodedatos.autores.adaptadores

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.R

class AutorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tvName)
    val tvBirthDate: TextView = view.findViewById(R.id.tvBirthDate)
}