package com.example.proyecto_divisa.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity (tableName = "Divisa")
public data class Divisa(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "ID")
        var ID: Int,
        @ColumnInfo(name = "monedaBase")
        var monedaBase: String,
        @ColumnInfo(name = "monedaDestino")
        var monedaDestino: String,
        @ColumnInfo(name = "conversion")
        var conversion: Double,
        @ColumnInfo(name = "fecha")
        var fecha: Date){

    @Ignore
    constructor() :
            this(0,"","",0.0,Date(0))
}