package com.example.ejercicio_tema_8.adapter

import com.example.ejercicio_tema_8.R
import com.example.ejercicio_tema_8.domain.ComunidadAutonoma

class ComunidadAutonomaProvider {

    companion object {
        var listaComunidadAutonoma = mutableListOf(
            ComunidadAutonoma(0, "Andalucía", R.drawable.andalucia),
            ComunidadAutonoma(1, "Aragón", R.drawable.aragon),
            ComunidadAutonoma(2, "Asturias", R.drawable.asturias),
            ComunidadAutonoma(3, "Baleares", R.drawable.baleares),
            ComunidadAutonoma(4, "Canarias", R.drawable.canarias),
            ComunidadAutonoma(5, "Cantabria", R.drawable.cantabria),
            ComunidadAutonoma(6, "Cataluña", R.drawable.catalunya),
            ComunidadAutonoma(7, "Castilla La Mancha", R.drawable.castilla_la_mancha),
            ComunidadAutonoma(8, "Castilla y León", R.drawable.castilla_y_leon),
            ComunidadAutonoma(9, "Comunidad de Madrid", R.drawable.madrid),
            ComunidadAutonoma(10, "Comunidad Foral de Navarra", R.drawable.navarra),
            ComunidadAutonoma(11, "Comunidad Valenciana", R.drawable.valencia),
            ComunidadAutonoma(12, "Extremadura", R.drawable.extremadura),
            ComunidadAutonoma(13, "Galicia", R.drawable.galicia),
            ComunidadAutonoma(14, "Murcia", R.drawable.murcia),
            ComunidadAutonoma(15, "La Rioja", R.drawable.rioja),
            ComunidadAutonoma(16, "País Vasco", R.drawable.pais_vasco),
        )
        //var nuevaLista = listaComunidadAutonoma.toList().toMutableList()
    }
}