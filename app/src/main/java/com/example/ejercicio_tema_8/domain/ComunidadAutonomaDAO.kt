package com.example.ejercicio_tema_8.domain

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class ComunidadAutonomaDAO {
    fun cargarLista(context: Context?): MutableList<ComunidadAutonoma> {
        lateinit var res: MutableList<ComunidadAutonoma>
        lateinit var c: Cursor
        try {
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            val sql = "SELECT * FROM comunidades WHERE estado = 'visible';"
            c = db.rawQuery(sql, null)
            res = mutableListOf()
            // Leer resultados del cursor e insertarlos en la lista
            while (c.moveToNext()) {
                val nueva = ComunidadAutonoma(
                    c.getInt(0), c.getString(1),
                    c.getInt(2), c.getInt(3),
                    c.getString(4), c.getDouble(5),
                    c.getDouble(6), c.getInt(7),
                    c.getString(9)
                )
                res.add(nueva)
            }
        } finally {
            c.close()
        }
        return res
    }

    fun obtenerComunidadAutonoma(context: Context?, id: Int): ComunidadAutonoma? {
        var comunidadAutonoma: ComunidadAutonoma?= null
        lateinit var c: Cursor
        try {
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            val sql = "SELECT * FROM comunidades WHERE estado = 'visible' AND id = ?;"
            val selectionArgs = arrayOf(id.toString())
            c = db.rawQuery(sql, selectionArgs)
            // Leo los resultados del cursor e insertarlos en la lista
            if (c.moveToNext()) {
                 comunidadAutonoma  = ComunidadAutonoma(
                    c.getInt(0), c.getString(1),
                    c.getInt(2), c.getInt(3),
                    c.getString(4), c.getDouble(5),
                    c.getDouble(6), c.getInt(7),
                    c.getString(9)
                )
            }
        } finally {
            c.close()
        }
        return comunidadAutonoma
    }


    fun borrarDeBBDD(context: Context?, nombre: String) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.execSQL("UPDATE comunidades SET estado='invisible' WHERE nombre='$nombre';")
        db.close()
    }

    fun actualizarBBDD(context: Context?, comunidad: ComunidadAutonoma) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase

        val values = ContentValues()
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ID, comunidad.id)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_NOMBRE, comunidad.nombre)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_IMAGEN, comunidad.imagen)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_HABITANTES, comunidad.habitantes)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_CAPITAL, comunidad.capital)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_LATITUD, comunidad.latitud)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_LONGITUD, comunidad.longitud)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ICONO, comunidad.icono)
        values.put(ComunidadAutonomaContract.Companion.Entrada.COLUMNA_URI, comunidad.uri)

        db.update(ComunidadAutonomaContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id=?",
            arrayOf(comunidad.id.toString())
        )
        db.close()
    }

    fun cambiarEstadoVisible(context: Context?) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.beginTransaction()
        try {
            db.execSQL("UPDATE comunidades SET estado='visible'")
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        db.close()
    }

    fun cambiarEstadoEliminado(context: Context?) {
        val db = DBOpenHelper.getInstance(context)!!.writableDatabase
        db.beginTransaction()
        try {
            db.execSQL("UPDATE comunidades SET estado='invisible'")
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        db.close()
    }
}