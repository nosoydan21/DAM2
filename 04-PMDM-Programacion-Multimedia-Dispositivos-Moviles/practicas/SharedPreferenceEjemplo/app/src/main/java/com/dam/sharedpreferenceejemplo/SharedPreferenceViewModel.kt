package com.dam.sharedpreferenceejemplo

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "settings")

class SharedPreferenceViewModel : ViewModel() {

    //    definimos las keys que necesitamos
    companion object {
        val NOMBRE_KEY = stringPreferencesKey("nombre")
        val EDAD_KEY = intPreferencesKey("edad")
        val ESTATURA_KEY = stringPreferencesKey("estatura")
        val NOTIFICACIONES_KEY = stringPreferencesKey("notificaciones")
        val PERFIL_PUBLIC_KEY = stringPreferencesKey("perfil_publico")

    }

    //    definimos los getters y setters
    fun getNombre(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[NOMBRE_KEY] ?: ""
        }
    }
    fun setNombre(context: Context, nombre: String) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[NOMBRE_KEY] = nombre
            }
        }
    }
}