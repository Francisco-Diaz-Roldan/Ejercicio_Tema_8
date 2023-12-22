package com.example.ejercicio_tema_8.domain

data class ComunidadAutonoma(val id:Int, var nombre:String, val imagen:Int, val habitantes:Int,
    val capital: String, val latitud:Double, val longitud:Double, val icono:Int) {
}