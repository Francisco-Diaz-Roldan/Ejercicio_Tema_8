package com.example.ejercicio_tema_8.adapter

import com.example.ejercicio_tema_8.R
import com.example.ejercicio_tema_8.domain.ComunidadAutonoma

class ComunidadAutonomaProvider {

    companion object {
        var listaComunidadAutonoma = mutableListOf(
            ComunidadAutonoma(0, "Andalucía", R.drawable.andalucia,8472407 ,"Sevilla",37.56640275933285 ,-4.7406737719892265, R.drawable.andalucia_icon, ""),
            ComunidadAutonoma(1, "Aragón", R.drawable.aragon,1326261,"Zaragoza",41.61162981125681, -0.9738034948937436,R.drawable.aragon_icon, ""),
            ComunidadAutonoma(2, "Asturias", R.drawable.asturias,1011792,"Oviedo",43.45998093597627, -5.864665888274809,R.drawable.asturias_icon, ""),
            ComunidadAutonoma(3, "Baleares", R.drawable.baleares,1173008,"Palma de Mallorca",39.57880491837696, 2.904506700284016,R.drawable.baleares_icon, ""),
            ComunidadAutonoma(4, "Canarias", R.drawable.canarias,2172944,"Las Palmas de GC y SC de Tenerife",28.334567287944736, -15.913870062646897,R.drawable.canarias_icon, ""),
            ComunidadAutonoma(5, "Cantabria", R.drawable.cantabria,584507,"Santander",43.36511077650701, -3.8398424912727958,R.drawable.cantabria_icon, ""),
            ComunidadAutonoma(6, "Cataluña", R.drawable.catalunya,7763362,"Barcelona",42.07542633707148, 1.5197485699265891,R.drawable.catalunya_icon, ""),
            ComunidadAutonoma(7, "Castilla La Mancha", R.drawable.castilla_la_mancha,2049562,"No tiene (Toledo)",39.42393852713387, -3.4784057150456764,R.drawable.castillamancha_icon, ""),
            ComunidadAutonoma(8, "Castilla y León", R.drawable.castilla_y_leon,2383139,"No tiene (Valladolid)",41.82966675375594, -4.841538702082391,R.drawable.castillaleon_icon, ""),
            ComunidadAutonoma(9, "Comunidad de Madrid", R.drawable.madrid,6751251,"Madrid",40.429642598652, -3.76167856716930,R.drawable.madrid_icon, ""),
            ComunidadAutonoma(10, "Comunidad de Navarra", R.drawable.navarra,661537,"Pamplona",42.71764719490406, -1.657559057849277,R.drawable.navarra_icon, ""),
            ComunidadAutonoma(11, "Comunidad Valenciana", R.drawable.valencia,5058138, "Valencia",39.515011403926145, -0.6939076854376838,R.drawable.valencia_icon, ""),
            ComunidadAutonoma(12, "Extremadura", R.drawable.extremadura,1059501,"Mérida",39.05050233766541, -6.351254430283863,R.drawable.extremadura_icon, ""),
            ComunidadAutonoma(13, "Galicia", R.drawable.galicia,2695645,"Santiago de Compostela",42.789055617025404, -7.996440102093343,R.drawable.galicia_icon, ""),
            ComunidadAutonoma(14, "Murcia", R.drawable.murcia,1518486,"Murcia",38.088904824462176, -1.4100155858243844,R.drawable.murcia_icon, ""),
            ComunidadAutonoma(15, "La Rioja", R.drawable.rioja,319796,"Logroño",42.568072855089895, -2.470916178908127,R.drawable.larioja_icon, ""),
            ComunidadAutonoma(16, "País Vasco", R.drawable.pais_vasco,2213993,"Vitoria",43.11260202399828, -2.594687915428055,R.drawable.paisvasco_icon, ""),
            ComunidadAutonoma(17,"Ceuta",  R.drawable.ceuta,83517,"Ceuta",35.90091766842379, -5.309980167928874,R.drawable.ceuta_icon, ""),
            ComunidadAutonoma(18,"Melilla",  R.drawable.melilla,86261,"Melilla",35.34689811596408, -2.957162284523383,R.drawable.melilla_icon, "")

        )
        //var nuevaLista = listaComunidadAutonoma.toList().toMutableList()
    }
}