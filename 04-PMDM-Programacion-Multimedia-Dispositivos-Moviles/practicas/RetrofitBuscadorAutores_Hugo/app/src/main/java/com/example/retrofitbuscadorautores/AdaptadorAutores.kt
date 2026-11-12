package com.example.retrofitbuscadorautores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import okhttp3.EventListener

class AdaptadorAutores : RecyclerView.Adapter<AutoresViewHolder>(){
    private var aut: List<Autores> =mutableListOf()
    private lateinit var listener: AdaptadorAutoresCallback;

    interface AdaptadorAutoresCallback{
        fun onSaveAutor(autores: Autores)
    }

    fun setAdaptadorAutoresCallback(listener: AdaptadorAutoresCallback){
        this.listener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AutoresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AutoresViewHolder(layoutInflater.inflate(R.layout.fila_autores,
            parent, false))
    }
    override fun getItemCount(): Int = aut.size

    fun changelist(autores: List<Autores>) {
        aut=autores
    }

    override fun onBindViewHolder(holder: AutoresViewHolder, position: Int) {
        val item = aut[position]
        holder.nombre.text = item.name
        holder.toolbarFavorito.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_favoritos -> {
                    listener.onSaveAutor(item)
                    true
                }

                else -> {
                    true
                }
            }
        }
    }
}