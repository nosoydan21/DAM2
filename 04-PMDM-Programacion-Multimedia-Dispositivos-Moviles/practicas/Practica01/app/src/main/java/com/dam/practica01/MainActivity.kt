package com.dam.practica01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNum1 = findViewById<EditText>(R.id.etNum1)
        val etNum2 = findViewById<EditText>(R.id.etNum2)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        fun leerNumeros(): Pair<Double, Double>? {
            val a = etNum1.text.toString().toDoubleOrNull()
            val b = etNum2.text.toString().toDoubleOrNull()
            if (a == null || b == null) {
                tvResultado.text = getString(R.string.err_num)
                return null
            }
            return Pair(a, b)
        }

        findViewById<Button>(R.id.btnSum).setOnClickListener {
            leerNumeros()?.let { (a, b) ->
                tvResultado.text = getString(R.string.resultado_valor, a + b)
            }
        }

        findViewById<Button>(R.id.btnRes).setOnClickListener {
            leerNumeros()?.let { (a, b) ->
                tvResultado.text = getString(R.string.resultado_valor, a - b)
            }
        }

        findViewById<Button>(R.id.btnMul).setOnClickListener {
            leerNumeros()?.let { (a, b) ->
                tvResultado.text = getString(R.string.resultado_valor, a * b)
            }
        }

        findViewById<Button>(R.id.btnDiv).setOnClickListener {
            leerNumeros()?.let { (a, b) ->
                tvResultado.text =
                    if (b == 0.0) getString(R.string.error_div_cero)
                    else getString(R.string.resultado_valor, a / b)
            }
        }
    }
}