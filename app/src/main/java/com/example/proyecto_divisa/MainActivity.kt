package com.example.proyecto_divisa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.proyecto_divisa.work_manager.InsertarDivisaWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private val workManager = WorkManager.getInstance(application)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED) //TODO: Revisar si es el tipo adecuado
            .build()

        val workRequest =
            PeriodicWorkRequestBuilder<InsertarDivisaWorker>(20, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork("Insert work", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }
}