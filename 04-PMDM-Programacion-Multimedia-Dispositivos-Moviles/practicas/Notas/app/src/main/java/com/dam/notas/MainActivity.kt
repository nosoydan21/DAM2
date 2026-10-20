package com.dam.notas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    
    private lateinit var startActivityForResult: ActivityResultLauncher<Intent>;
    private lateinit var addNotaButton: Button
    private lateinit var rvNotas: RecyclerView
    private var adaptadorRecyclerViewnotas = AdaptadorNotas()

    private lateinit var startAddNotaForResult: ActivityResultLauncher<Intent>

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_file -> {
            Log.i("ActionBar", "Archivo!")
            true
        }
        R.id.action_buscar -> {
            Log.i("ActionBar", "Buscar!")
            true
        }
        R.id.action_opciones -> {
            Log.i("ActionBar", "Opciones!")
            true
        }
        R.id.action_salir -> {
            Log.i("ActionBar", "Salir!")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(findViewById(R.id.toolbarPrincipal))

        //bindear
        addNotaButton=findViewById(R.id.addNotaBoton)
        rvNotas=findViewById(R.id.recyclerViewNotas)
        rvNotas.adapter=adaptadorRecyclerViewnotas

        //La línea que divide elementos
        rvNotas.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        //listener
        addNotaButton.setOnClickListener{
            val intent = Intent(this, AddNotaActivity::class.java)
            startActivityForResult.launch(intent)
        }

        startActivityForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            result ->
            if (result.resultCode == RESULT_OK){
                val data = result.data
                if(data!=null){
                    val titulo = data.getStringExtra("titulo")
                    val descripcion = data.getStringExtra("descripcion")
                    adaptadorRecyclerViewnotas.addNota(Nota(titulo!!, descripcion!!))
                }
            }
        }

        startAddNotaForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val tituloNota = data.extras?.getString("titulo")?:""
                    val descnota = data.extras?.getString("descripcion")?:""
                    adaptadorRecyclerViewnotas.addNota(Nota(tituloNota, descnota))
                }
            }
        }


        addNotaButton.setOnClickListener {
            val intent = Intent(this, AddNotaActivity::class.java)
            startAddNotaForResult.launch(intent)  // Lanzar la ac
        }


    }
}