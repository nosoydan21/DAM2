package com.dam.relojlivedata

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel: ViewModel() {
    private val _hora = MutableStateFlow("")
    private var job: Job? = null


    val hora: StateFlow<String> = _hora


    fun iniciarReloj() {
        job?.cancel()


        job = viewModelScope.launch {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            while (isActive) {
                val millis = System.currentTimeMillis()
                val date = Date(millis)
                _hora.value = sdf.format(date)


                delay(1000) // esperar 1 segundo
            }
        }
    }


    fun detenerReloj() {
        job?.cancel()
    }
    fun startStopReloj() {
        if(job?.isActive == true){
            detenerReloj()
        }else
        {
            iniciarReloj()
        }

    }
}