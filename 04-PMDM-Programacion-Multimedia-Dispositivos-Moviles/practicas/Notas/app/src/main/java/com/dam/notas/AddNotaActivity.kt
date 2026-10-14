package com.dam.notas

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddNotaActivity: AppCompatActivity() {
    private lateinit var buttonAdd: Button
    private lateinit var etTitulo: EditText
    private lateinit var etDescripcion: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_nota)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //bindeo
        buttonAdd=findViewById(R.id.buttonAddNota)
        etTitulo=findViewById(R.id.editTextTituloNota)
        etDescripcion=findViewById(R.id.editTextDescripcionNota)

        //oyente
        buttonAdd.setOnClickListener{
            if (etTitulo.text.isNotEmpty() && etDescripcion.text.isNotEmpty()){
                //Hacemos algo
                val resultIntent= Intent()
                resultIntent.putExtra("titulo", etTitulo.text.toString())
                resultIntent.putExtra("descripcion", etDescripcion.text.toString())
                setResult(RESULT_OK, resultIntent)
                finish()

            } else {
                //Mostramos un advertencia
                Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}