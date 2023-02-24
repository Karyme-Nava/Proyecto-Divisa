package com.example.proyecto_divisa.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity (tableName = "Moneda")
public data class Moneda(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "ID")
        var ID: Int,
        @ColumnInfo(name = "codigo")
        var codigo: String,
        @ColumnInfo(name = "nombre")
        var nombre: String,
        @ColumnInfo(name = "pais")
        var pais: String){

    @Ignore
    constructor() :
            this(0,"","","")
}