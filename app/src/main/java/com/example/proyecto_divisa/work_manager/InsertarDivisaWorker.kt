package com.example.proyecto_divisa.work_manager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.proyecto_divisa.Aplicacion
import com.example.proyecto_divisa.db.DivisaDatabase
import com.example.proyecto_divisa.model.Divisa
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class InsertarDivisaWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    lateinit var bd : DivisaDatabase
    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result{
        return try {
            var d = Divisa(0, "USD", "MXN", 18.1, LocalDateTime.now().toString())
            bd = (applicationContext as Aplicacion).database
            GlobalScope.launch { bd.getDivisaDAO().insertar(d) }
            Result.success()
        } catch (throwable: Throwable) {
            Log.e("", "Error al insertar")
            Result.failure()
        }

    }
}