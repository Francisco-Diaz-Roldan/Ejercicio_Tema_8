package com.example.ejercicio_tema_8.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ejercicio_tema_8.adapter.ComunidadAutonomaProvider
import java.lang.Exception

class DBOpenHelper private constructor(context: Context?):
    SQLiteOpenHelper(context, ComunidadContract.NOMBRE_BD, null, ComunidadContract.VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                "CREATE TABLE ${ComunidadContract.Companion.Entrada.NOMBRE_TABLA}"
                        + "(${ComunidadContract.Companion.Entrada.COLUMNA_ID} int NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE} NVARCHAR(20) NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN} int NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_HABITANTES} int NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_CAPITAL} NVARCHAR(20) NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_LATITUD} REAL NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_LONGITUD} REAL NOT NULL"
                        + ",${ComunidadContract.Companion.Entrada.COLUMNA_ICONO} int NOT NULL" +
                        ",${ComunidadContract.Companion.Entrada.COLUMNA_ESTADO} TEXT CHECK(${ComunidadContract.Companion.Entrada.COLUMNA_ESTADO} IN ('activo', 'eliminado')) DEFAULT 'activo');")

            // Inserto los datos en la tabla
            inicializarBBDD(sqLiteDatabase)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${ComunidadContract.Companion.Entrada.NOMBRE_TABLA};")
        onCreate(sqLiteDatabase)
    }

    private fun inicializarBBDD(db: SQLiteDatabase) {
        val lista = ComunidadAutonomaProvider.listaComunidadAutonoma
        for (comunidad in lista) {
            db.execSQL(
                "INSERT INTO ${ComunidadContract.Companion.Entrada.NOMBRE_TABLA}(" +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_ID}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_NOMBRE}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_IMAGEN}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_HABITANTES}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_CAPITAL}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_LATITUD}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_LONGITUD}," +
                        "${ComunidadContract.Companion.Entrada.COLUMNA_ICONO})" +
                        " VALUES (${comunidad.id},'${comunidad.nombre}',${comunidad.imagen}," +
                        "${comunidad.habitantes},'${comunidad.capital}',${comunidad.latitud}," +
                        "${comunidad.longitud},${comunidad.icono});"
            )
        }
    }

    companion object {
        private var dbOpen: DBOpenHelper? = null
        fun getInstance(context: Context?): DBOpenHelper? {
            if (dbOpen == null) dbOpen = DBOpenHelper(context)
            return dbOpen
        }
    }
}