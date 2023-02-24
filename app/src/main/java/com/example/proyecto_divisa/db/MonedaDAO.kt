package com.example.proyecto_divisa.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import com.example.proyecto_divisa.model.Moneda
import java.util.concurrent.Flow

@Dao
interface MonedaDAO {
    @Insert
    suspend fun insertar(moneda: Moneda)

    @Query("select * from Moneda")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Moneda>>

    @Query("DELETE FROM Moneda")
    suspend fun deleteAll() : Int

    @Query("select ID, codigo, nombre, pais from Moneda")
    fun getAllCursor(): Cursor
}