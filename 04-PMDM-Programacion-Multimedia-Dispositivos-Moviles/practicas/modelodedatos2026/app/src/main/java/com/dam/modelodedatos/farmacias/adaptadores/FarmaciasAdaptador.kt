package com.dam.modelodedatos.farmacias.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.R
import com.dam.modelodedatos.farmacias.MurciaRecord

class FarmaciasAdaptador(private val records: List<MurciaRecord>) : RecyclerView.Adapter<FarmaciasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmaciasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fila_farmacias, parent, false)
        return FarmaciasViewHolder(view)
    }

    override fun onBindViewHolder(holder: FarmaciasViewHolder, position: Int) {
        val record = records[position]
        holder.tvTitle.text = record.nombre ?: record.denominacion ?: "Sin nombre"
        holder.tvMunicipio.text = record.municipio ?: "Sin municipio"
    }

    override fun getItemCount() = records.size
}