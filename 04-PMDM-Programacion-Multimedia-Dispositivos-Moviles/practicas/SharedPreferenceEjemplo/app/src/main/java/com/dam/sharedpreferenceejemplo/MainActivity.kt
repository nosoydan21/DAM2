package com.dam.sharedpreferenceejemplo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.jvm.java


class MainActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etEstatura: EditText
    private lateinit var swNotificaciones: MaterialSwitch
    private lateinit var swPerfilPublico: MaterialSwitch
    private lateinit var btnGuardar: Button
    private lateinit var btnCargar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var tvResumen: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etEstatura = findViewById(R.id.etEstatura)
        swNotificaciones = findViewById(R.id.swNotificaciones)
        swPerfilPublico = findViewById(R.id.swPerfilPublico)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCargar = findViewById(R.id.btnCargar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        tvResumen = findViewById(R.id.tvResumen)

        val viewModelSP = ViewModelProvider(this)[SharedPreferenceViewModel::class.java]

        lifecycleScope.launch {
            viewModelSP.getNombre(this@MainActivity).collect { name ->
                etNombre.setText(name)
            }
        }

        viewModelSP.setNombre(this@MainActivity, "He cambiado mi nombre")

        // Configurar botones
        btnGuardar.setOnClickListener {
            guardarPreferencias()
        }


        btnCargar.setOnClickListener {
            cargarPreferencias()
            Toast.makeText(this, getString(R.string.msg_cargado), Toast.LENGTH_SHORT).show()
        }


        btnLimpiar.setOnClickListener {
            limpiarPreferencias()
        }


        // Cargar automáticamente las preferencias guardadas al iniciar
//        cargarPreferencias()
    }


    private fun guardarPreferencias() {
        val nombre: String = etNombre.text.toString()
        val edadStr: String = etEdad.text.toString()
        val estaturaStr: String = etEstatura.text.toString()


        val edad: Int = edadStr.toIntOrNull() ?: 0
        val estatura: Float = estaturaStr.toFloatOrNull() ?: 0.0f
        val notificaciones: Boolean = swNotificaciones.isChecked
        val perfilPublico: Boolean = swPerfilPublico.isChecked


        val prefs: SharedPreferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        prefs.edit {


            putString("nombre", nombre)
            putInt("edad", edad)
            putFloat("estatura", estatura)
            putBoolean("notificaciones", notificaciones)
            putBoolean("perfilPublico", perfilPublico)


        }


        Toast.makeText(this, getString(R.string.msg_guardado), Toast.LENGTH_SHORT).show()
        cargarPreferencias()
    }


    private fun cargarPreferencias() {
        val prefs: SharedPreferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)


        val nombre: String = prefs.getString("nombre", "") ?: ""
        val edad: Int = prefs.getInt("edad", 0)
        val estatura: Float = prefs.getFloat("estatura", 0.0f)
        val notificaciones: Boolean = prefs.getBoolean("notificaciones", false)
        val perfilPublico: Boolean = prefs.getBoolean("perfilPublico", false)


        // Asignar los valores a los elementos del formulario
        etNombre.setText(nombre)
        etEdad.setText(if (edad > 0) edad.toString() else "")
        etEstatura.setText(if (estatura > 0f) estatura.toString() else "")
        swNotificaciones.isChecked = notificaciones
        swPerfilPublico.isChecked = perfilPublico


        // Mostrar resumen en pantalla
        if (nombre.isNotEmpty() || edad > 0 || estatura > 0f) {
            tvResumen.text = " " +
                    "Nombre (String): $nombre \n" +
                    "Edad (Int): $edad años \n" +
                    "Estatura (Float): $estatura m \n" +
                    "Notificaciones (Boolean): ${if (notificaciones) "Activadas" else "Desactivadas"} \n" +
                    "Perfil Público (Boolean): ${if (perfilPublico) "Sí" else "No"} \n"
        } else {
            tvResumen.setText(R.string.no_datos)
        }
    }


    private fun limpiarPreferencias() {
        val prefs: SharedPreferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()


        etNombre.setText("")
        etEdad.setText("")
        etEstatura.setText("")
        swNotificaciones.isChecked = false
        swPerfilPublico.isChecked = false
        tvResumen.setText(R.string.no_datos)


        Toast.makeText(this, getString(R.string.msg_borrado), Toast.LENGTH_SHORT).show()
    }
}
