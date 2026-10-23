package com.dam.relojlivedata

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private lateinit var textViewHora: TextView
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textViewHora = findViewById(R.id.textView)
        textViewHora.setOnClickListener {
            startStopReloj()
        }


        iniciarReloj()
    }


    private fun iniciarReloj() {
        // Cancelamos cualquier job previo
        job?.cancel()
        job = lifecycleScope.launch {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())


            while (isActive) {
                val millis = System.currentTimeMillis()
                val date = Date(millis)
                textViewHora.text = sdf.format(date)
                delay(1000) // esperar 1 segundo
            }
        }
    }


    fun detenerReloj() {
        job?.cancel()
    }


    fun startStopReloj() {
        if (job?.isActive == true) {
            detenerReloj()
        } else {
            iniciarReloj()
        }

    }


}
