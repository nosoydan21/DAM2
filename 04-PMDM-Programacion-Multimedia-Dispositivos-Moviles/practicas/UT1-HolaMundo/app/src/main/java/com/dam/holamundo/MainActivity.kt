package com.dam.holamundo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        Log.d("estados","Ejecución onCreate")
    }

    override fun onStart(){
        super.onStart()
        Log.d("estados", "Ejecución de onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("estados","Ejecución onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("estados","Ejecución onPause")
    }

    override fun onStop() { // tiene mas tiempo de ejecucion, que el onDestroy
        super.onStop()
        Log.d("estados","Ejecución onStop")
    }

    override fun onDestroy() { // tiene 1s, todo lo que este fuera de ese tiempo se pierde, mejor usar el onStop
        super.onDestroy()
        Log.d("estados","Ejecución onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("estados","Ejecución onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("estados","Ejecución onSaveIntanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("estados","Ejecución onRestoreInstanceState")
    }
}