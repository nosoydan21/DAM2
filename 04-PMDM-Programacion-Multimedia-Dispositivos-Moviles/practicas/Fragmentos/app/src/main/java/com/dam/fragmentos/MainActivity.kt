package com.dam.fragmentos

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1=findViewById(R.id.boton1)
        btn2=findViewById(R.id.boton2)

        val firstFragment = FragmentoUno()
        val secondFragment = FragmentoDos()

        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.staticFragment, firstFragment)
                .commit()
        }

        btn2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.staticFragment, firstFragment)
                .commit()
        }

    }
}