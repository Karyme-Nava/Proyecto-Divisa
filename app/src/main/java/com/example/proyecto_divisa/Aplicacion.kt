package com.example.proyecto_divisa

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.proyecto_divisa.db.DivisaDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class Aplicacion : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { DivisaDatabase.getDatabase(this, applicationScope) }

    override fun onCreate() {
        super.onCreate()
    }


}