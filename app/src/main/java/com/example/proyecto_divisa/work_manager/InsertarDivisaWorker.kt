package com.example.proyecto_divisa.work_manager

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.proyecto_divisa.Aplicacion
import com.example.proyecto_divisa.db.DivisaDatabase
import com.example.proyecto_divisa.model.Divisa
import java.time.LocalDateTime
import com.example.proyecto_divisa.network.exchangeRateApiService
import android.content.ContentValues.TAG

class InsertarDivisaWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {
    lateinit var bd : DivisaDatabase
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result{
        return try {
            bd = (applicationContext as Aplicacion).database

            val response = exchangeRateApiService.getExchangeRates("USD/")

            response.conversionRates?.forEach { (key, value) ->
              var d = Divisa(0, "USD", "$key", value, LocalDateTime.now().toString())
                bd.getDivisaDao().insertar(d)
            }


            Result.success()
        } catch (throwable: Throwable) {
            Log.e("", "Error al insertar")
            Result.failure()
        }

    }
}