package com.example.proyecto_divisa.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_divisa.model.Moneda

@Dao
interface MonedaDAO {
    @Insert
    suspend fun insertar(moneda: Moneda) : Long

    @Update
    suspend fun actualizar(moneda: Moneda) : Int

    @Query("delete from Moneda WHERE ID = :id")
    suspend fun eliminar(id: Int) : Int

    @Query("delete from Moneda")
    suspend fun eliminarTodas() : Int

    @Query("select * from Moneda WHERE ID = :id")
    fun obtenerCursor(id: Int): Cursor

    @Query("select * from Moneda")
    fun obtenerTodasCursor(): Cursor
}