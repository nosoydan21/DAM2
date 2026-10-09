package com.dam.actividades

import android.content.Intent
import android.os.Bundle
import android.provider.Browser
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var boton: Button;
    private lateinit var startForResult: ActivityResultLauncher<Intent>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //bindeo
    boton=findViewById(R.id.botonActividad)
    navegador=findViewById(R.id.botonBrowser)

    //oyente
    navegador.setOnClickListener
    {
        //creamos un intent
        val browserIntent = Intent(Intent.ACTION_VIEW, "https://www.xataka.com".toUri())
        if (browserIntent.resolveActivity(packageManager) != null) {
            //lanzamos actividad
            startActivity(intent)
        }
    }

    //oyente
    boton.setOnClickListener
    {
        //creamos un intent
        val intent = Intent(this, SegundaActividad::class.java)
        //lanzamos actividad
        startActivity(intent)
    }
}