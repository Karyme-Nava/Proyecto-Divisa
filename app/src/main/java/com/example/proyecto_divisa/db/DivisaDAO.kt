package com.example.proyecto_divisa.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.proyecto_divisa.model.Divisa
import com.example.proyecto_divisa.model.Moneda
import java.util.concurrent.Flow

@Dao
interface DivisaDAO {
    @Insert
    suspend fun insertar(divisa: Divisa)
}