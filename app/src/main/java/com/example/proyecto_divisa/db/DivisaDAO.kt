package com.example.proyecto_divisa.db

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Update
import com.example.proyecto_divisa.model.Divisa
import com.example.proyecto_divisa.model.Moneda
import java.util.concurrent.Flow

@Dao
interface DivisaDAO {
    @Insert
    suspend fun insertar(divisa: Divisa) : Long

    @Update
    suspend fun actualizar(divisa: Divisa) : Int

    @Query("delete from Divisa WHERE _ID = :id")
    suspend fun eliminar(id: Int) : Int

    @Query("delete from Divisa")
    suspend fun eliminarTodas() : Int

    @Query("select * from Divisa WHERE _ID = :id")
    fun obtenerCursor(id: Int): Cursor

    @Query("select * from Divisa")
    fun obtenerTodasCursor(): Cursor
}