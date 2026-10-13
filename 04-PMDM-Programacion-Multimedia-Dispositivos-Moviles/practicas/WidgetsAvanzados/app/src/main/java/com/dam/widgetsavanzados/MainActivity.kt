package com.dam.widgetsavanzados

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //bindeamos
        spinner=findViewById(R.id.spinner)

/*
        //creamos el array de datos
        val datos = arrayOf("Elemento1","Elemento2","Elemento3","Elemento4","Elemento5")
        //creamos el adaptador usando el helper generico
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,datos)
        //asignamos el layout cuando se despliega
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //asignamos el adaptador recien creado a nuestro spinner
        spinner.adapter=adaptador
*/

        val adaptador = ArrayAdapter.createFromResource(this,R.array.valores_array,android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adaptador

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val pos = parent.getItemAtPosition(position)


                Toast.makeText(this@MainActivity, "se ha seleccionado $pos", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                TODO("Not yet implemented")
            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

    }
}