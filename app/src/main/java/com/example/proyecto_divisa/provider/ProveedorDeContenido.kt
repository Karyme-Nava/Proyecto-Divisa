package com.example.proyecto_divisa.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.proyecto_divisa.Aplicacion
import com.example.proyecto_divisa.db.DivisaDatabase
import com.example.proyecto_divisa.model.Divisa
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    addURI("com.example.proyecto_divisa", "divisa", 1)
    addURI("com.example.proyecto_divisa", "divisa/#", 2) ///id
    addURI("com.example.proyecto_divisa", "divisa/*", 3) ///fecha
}

class ProveedorDeContenido : ContentProvider() {
    lateinit var bd : DivisaDatabase
    override fun onCreate(): Boolean {
        bd = (context as Aplicacion).database
        return true
    }

    override fun getType(uri: Uri): String? {
        var typeMime = ""
        when(uriMatcher.match(uri)){
            1 -> typeMime = "vnd.android.cursor.dir/vnd.com.example.proyecto_divisa.Divisa"
            2 -> typeMime = "vnd.android.cursor.item/vnd.com.example.proyecto_divisa.Divisa"
            else -> typeMime = "vnd.android.cursor.item/vnd.com.example.proyecto_divisa.Divisa"
        }
        return typeMime
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?=null
        when(uriMatcher.match(uri)){
            1 -> cursor = bd.getDivisaDAO().obtenerTodasCursor()
            2 -> cursor = bd.getDivisaDAO().obtenerCursor(uri.lastPathSegment!!.toInt())
            3 -> throw IllegalAccessException("URI desconocida ${uri}")
        }
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var d = convertir(values)
        GlobalScope.launch { d.ID = bd.getDivisaDAO().insertar(d).toInt() }
        return Uri.parse("com.example.proyecto_divisa/divisa/${d.ID}")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var num = 0
        when(uriMatcher.match(uri)){
            1 -> GlobalScope.launch { num = bd.getDivisaDAO().eliminarTodas() }
            2 -> GlobalScope.launch { num = bd.getDivisaDAO().eliminar(uri.lastPathSegment!!.toInt()) }
            3 -> throw IllegalAccessException("URI desconocida ${uri}")
        }
        return num
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        var num = 0
        GlobalScope.launch { num = bd.getDivisaDAO().actualizar(convertir(values)) }
        return num
    }

    fun convertir(valores: ContentValues?): Divisa{
        val d = Divisa()
        d.ID = valores!!.getAsInteger("ID")
        d.monedaBase = valores!!.getAsString("monedaBase")
        d.monedaDestino = valores!!.getAsString("monedaDestino")
        d.conversion = valores!!.getAsDouble("conversion")
        d.fecha = valores!!.getAsString("fecha")
        return d
    }
}