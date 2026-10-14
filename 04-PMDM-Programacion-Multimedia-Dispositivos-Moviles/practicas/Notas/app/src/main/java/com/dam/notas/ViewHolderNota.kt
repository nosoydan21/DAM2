package com.dam.notas

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderNota(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView
    val textViewDescripcion: TextView


    init{
        textViewTitulo=itemView.findViewById(R.id.filaTituloNota)
        textViewDescripcion=itemView.findViewById(R.id.filaDescNota)
    }
}
