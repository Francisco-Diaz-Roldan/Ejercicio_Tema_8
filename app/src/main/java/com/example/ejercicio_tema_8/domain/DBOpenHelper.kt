package com.example.ejercicio_tema_8.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ejercicio_tema_8.adapter.ComunidadAutonomaProvider

class DBOpenHelper private constructor(context: Context?):
    SQLiteOpenHelper(context, ComunidadAutonomaContract.NOMBRE_BD, null, ComunidadAutonomaContract.VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                "CREATE TABLE ${ComunidadAutonomaContract.Companion.Entrada.NOMBRE_TABLA}"
                        + "(${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ID} int NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_NOMBRE} NVARCHAR(20) NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_IMAGEN} int NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_HABITANTES} int NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_CAPITAL} NVARCHAR(20) NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_LATITUD} REAL NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_LONGITUD} REAL NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ICONO} int NOT NULL"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ESTADO} TEXT CHECK(${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ESTADO} IN ('visible', 'invisible')) DEFAULT 'visible'"
                        + ",${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_URI} NVARCHAR(120) );")

            // Inserto los datos en la tabla
            inicializarBBDD(sqLiteDatabase)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${ComunidadAutonomaContract.Companion
            .Entrada.NOMBRE_TABLA};")
        onCreate(sqLiteDatabase)
    }

    private fun inicializarBBDD(db: SQLiteDatabase) {
        val lista = ComunidadAutonomaProvider.listaComunidadAutonoma
        for (comunidad in lista) {
            db.execSQL(
                "INSERT INTO ${ComunidadAutonomaContract.Companion.Entrada.NOMBRE_TABLA}(" +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ID}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_NOMBRE}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_IMAGEN}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_HABITANTES}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_CAPITAL}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_LATITUD}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_LONGITUD}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_ICONO}," +
                        "${ComunidadAutonomaContract.Companion.Entrada.COLUMNA_URI})" +
                        " VALUES (${comunidad.id},'${comunidad.nombre}',${comunidad.imagen}," +
                        "${comunidad.habitantes},'${comunidad.capital}',${comunidad.latitud}," +
                        "${comunidad.longitud},${comunidad.icono},'${comunidad.uri}');"
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