package com.dam.notas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdaptadorNotas(): RecyclerView.Adapter<ViewHolderNota>() {
    private var notas: MutableList<Nota> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderNota {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.fila_notas, parent, false)
        val viewHolder = ViewHolderNota(vista)
        return viewHolder
    }


    override fun onBindViewHolder(holder: ViewHolderNota, position: Int) {
        val nota: Nota=this.notas[position]
        holder.textViewTitulo.text=nota.titulo
        holder.textViewDescripcion.text=nota.descripcion


    }

    override fun getItemCount(): Int {
        return this.notas.size
    }

    fun addNota(nota: Nota){
        notas.add(nota)
        notifyDataSetChanged();
    }

}
