package com.dam.modelodedatos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dam.modelodedatos.autores.AutorClient
import com.dam.modelodedatos.autores.adaptadores.AutorAdaptador
import kotlinx.coroutines.launch

class AutorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_con_recycler, container, false)
        return view
    }


}