package com.dam.modelodedatos.universidades.adaptadores

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.R

class UniversidadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName: TextView = view.findViewById(R.id.tvUniName)
    val tvWeb: TextView = view.findViewById(R.id.tvUniWeb)
}