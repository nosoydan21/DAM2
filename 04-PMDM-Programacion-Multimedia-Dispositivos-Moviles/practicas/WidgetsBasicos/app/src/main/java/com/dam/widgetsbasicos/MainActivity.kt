package com.dam.widgetsbasicos

import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.filament.View
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var primEtiqueta: TextView;
    private lateinit var boton: Button;
    private lateinit var editTextNumber: EditText;
    private lateinit var logo: ImageView;
    private lateinit var radioGroup: RadioGroup;
    private lateinit var botonRG: Button;
    private lateinit var switch: Switch;
    private lateinit var checkBox: CheckBox;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        primEtiqueta = findViewById(R.id.main_etiqueta1)
        editTextNumber = findViewById(R.id.etEjemplo)

        val number = 4
        editTextNumber.SetText = number.toString()

        editTextNumber.addTextChangedListener(object : TextWatcher)


        primEtiqueta.text = "Le he cambiado la etiqueta"
        primEtiqueta.setOnClickListener {
            Log.d("asco", "he pulsado la etiqueta")
        }

        boton = findViewById(R.id.button_id)
        boton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.i("boton", "boton pulsado:")
            }
        })

//        boton = findViewById(R.id.button_id)
//        boton.setOnClickListener{
//            Log.i("boton", "boton pulsado:")
//        }


        //Image view
        logo.setImageResource(R.drawable.delete_24px)
        logo.setOnClickListener {
            Log.d("image", "pulsando imagen")
        }

        logo = findViewById(R.id.imageView)
        logo.setOnTouchListener { v: View, m: MotionEvent ->
            val action = m.action
            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("MainActivity", "Down")
                }

                MotionEvent.ACTION_MOVE -> {
                    Log.d("MainActivity", "Move")
                }

                MotionEvent.ACTION_UP -> {
                    Log.d("MainActivity", "Up")
                }
            }
            true
        }
        //Radio Group
        botonRG.setOnClickListener {
            //obtengo el id del radio marcado
            var id_rb: Int = radioGroup.checkedRadioButtonId
            //bindeo radio button con el id obtenido
            var rbSeleccionado = RadioButton.findViewById(id_rb)
            Log.d("RadioGroup", rbSeleccionado.text.toString())
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            Log.d("id radiogroup que lanza", group.toString())
            Log.d("id radioButton que esta seleccionado", checkedId.toString())
        }

        sw.setOnClickChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Toggle pulsado a on", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "deslizador ON")
            } else {
                Toast.makeText(this, "Toggle pulsado a OFF", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "deslizador OFF")

            }
        }

        checkBox.setOnClickListener {
            if (it is CheckBox) {
                if (it.isChecked)
                    checkBox.text = "Checkbox marcado!"
                else
                    checkBox.text = "Checkbox desmarcado!"
            }
        }




    }
}