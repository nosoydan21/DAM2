package com.dam.notas

import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView

class ViewHolderNota(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView
    val textViewDescripcion: TextView

    val menuNota: Toolbar


    init{
        textViewTitulo=itemView.findViewById(R.id.filaTituloNota)
        textViewDescripcion=itemView.findViewById(R.id.filaDescNota)
        menuNota=itemView.findViewById(R.id.tbNota)
        menuNota.inflateMenu(R.menu.menu_nota)
    }
}
