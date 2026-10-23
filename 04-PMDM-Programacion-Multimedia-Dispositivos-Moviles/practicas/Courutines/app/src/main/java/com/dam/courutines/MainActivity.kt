package com.dam.courutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numeros = 100000000


        println("Iniciando Hilo")
        lifecycleScope.launch(Dispatchers.Default) {
            var sum = 0L
            val startTime = System.currentTimeMillis()


            for (i in 0 until numeros) {
                sum += i
            }


            val endTime = System.currentTimeMillis()


            // Cambia al hilo principal (UI thread) para actualizar la interfaz o imprimir
            withContext(Dispatchers.Main) {
                println("Suma total: $sum")
                println("Tiempo total: ${endTime - startTime} ms")
            }
        }
        println("Fin metodo del Hilo")

    }


}