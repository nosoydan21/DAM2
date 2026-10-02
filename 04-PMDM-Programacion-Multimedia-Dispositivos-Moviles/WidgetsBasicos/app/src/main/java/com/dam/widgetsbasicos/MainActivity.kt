package com.dam.widgetsbasicos

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var primEtiqueta: TextView;
    private lateinit var editTextNumber: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primEtiqueta=findViewById(R.id.main_etiqueta1)
        editTextNumber=findViewById(R.id.etEjemplo)

        val number=4
        editTextNumber.text=setText(toString())


        primEtiqueta.text="Le he cambiado la etiqueta"
        primEtiqueta.setOnClickListener {
            Log.d("asco", "he pulsado la etiqueta")
        }
    }
}