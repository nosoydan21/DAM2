package com.dam.modelodedatos.farmacias.adaptadores

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.R

class FarmaciasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView = view.findViewById(R.id.tvNombre)
    val tvMunicipio: TextView = view.findViewById(R.id.tvMunicipio)
}