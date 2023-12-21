package com.example.ejercicio_tema_8.domain

import android.provider.BaseColumns

class ComunidadContract {
    companion object {
        const val NOMBRE_BD = "comunidadesAutónomas"
        const val VERSION = 1

        class Entrada : BaseColumns {
            companion object {
                const val NOMBRE_TABLA = "comunidades"
                const val COLUMNA_ID = "id"
                const val COLUMNA_NOMBRE = "nombre"
                const val COLUMNA_IMAGEN = "imagen"
                const val COLUMNA_ESTADO = "estado"
            }
        }
    }
}