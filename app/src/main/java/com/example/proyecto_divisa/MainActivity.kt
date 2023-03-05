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
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequestBuilder<InsertarDivisaWorker>(24, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork("Insert work", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }
}