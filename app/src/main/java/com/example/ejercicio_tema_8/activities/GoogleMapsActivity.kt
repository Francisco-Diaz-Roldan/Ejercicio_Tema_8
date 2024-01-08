package com.example.ejercicio_tema_8.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejercicio_tema_8.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.ejercicio_tema_8.databinding.ActivityGoogleMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGoogleMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

// Obtengo el SupportMapFragment y recibo las notificaciones cuando el mapa esté listo para ser utilizado.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        val nombre = intent.getStringExtra("comunidadNombre")
        val capital = intent.getStringExtra("comunidadCapital")
        val habitantes = intent.getStringExtra("comunidadHabitantes")
        val latitud = intent.getDoubleExtra("comunidadLatitud", 0.0)
        val longitud = intent.getDoubleExtra("comunidadLongitud", 0.0)
        mMap = googleMap


        val andalucia = LatLng(37.56640275933285, -4.7406737719892265)
        mMap.addMarker(
            MarkerOptions().position(andalucia)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.andalucia_icon))
                .title("Andalucía-Capital: Sevilla").snippet("Habitantes: 8472407")
        )

        val aragon = LatLng(41.61162981125681, -0.9738034948937436)
        mMap.addMarker(
            MarkerOptions().position(aragon)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.aragon_icon))
                .title("Aragón-Capital: Zaragoza").snippet("Habitantes: 1326261")
        )

        val asturias = LatLng(43.45998093597627, -5.864665888274809)
        mMap.addMarker(
            MarkerOptions().position(asturias)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.asturias_icon))
                .title("Asturias-Capital: Oviedo").snippet("Habitantes: 1011792")
        )

        val baleares = LatLng(39.57880491837696, 2.904506700284016)
        mMap.addMarker(
            MarkerOptions().position(baleares)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.baleares_icon))
                .title("Baleares-Capital: Palma de Mallorca").snippet("Habitantes: 1173008")
        )

        val canarias = LatLng(28.334567287944736, -15.913870062646897)
        mMap.addMarker(
            MarkerOptions().position(canarias)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.canarias_icon))
                .title("Canarias-Capital: Las Palmas de GC y SC de Tenerife")
                .snippet("Habitantes: 2172944")
        )

        val cantabria = LatLng(43.36511077650701, -3.8398424912727958)
        mMap.addMarker(
            MarkerOptions().position(cantabria)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cantabria_icon))
                .title("Cantabria-Capital: Santander").snippet("Habitantes: 584507")
        )

        val castillaLeon = LatLng(41.82966675375594, -4.841538702082391)
        mMap.addMarker(
            MarkerOptions().position(castillaLeon)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.castillaleon_icon))
                .title("Castilla y León-Capital: No tiene (Valladolid)")
                .snippet("Habitantes: 2383139")
        )

        val castillaMancha = LatLng(39.42393852713387, -3.4784057150456764)
        mMap.addMarker(
            MarkerOptions().position(castillaMancha)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.castillamancha_icon))
                .title("Castilla la Mancha-Capital: No tiene (Toledo)")
                .snippet("Habitantes: 2049562")
        )

        val catalunha = LatLng(42.07542633707148, 1.5197485699265891)
        mMap.addMarker(
            MarkerOptions().position(catalunha)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.catalunya_icon))
                .title("Cataluña-Capital: Barcelona").snippet("Habitantes: 7763362")
        )

        val ceuta = LatLng(35.90091766842379, -5.309980167928874)
        mMap.addMarker(
            MarkerOptions().position(ceuta)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ceuta_icon))
                .title("Ceuta-Capital: Ceuta").snippet("Habitantes: 83517")
        )

        val extremadura = LatLng(39.05050233766541, -6.351254430283863)
        mMap.addMarker(
            MarkerOptions().position(extremadura)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.extremadura_icon))
                .title("Extremadura-Capital: Mérida").snippet("Habitantes: 1059501")
        )

        val galicia = LatLng(42.789055617025404, -7.996440102093343)
        mMap.addMarker(
            MarkerOptions().position(galicia)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.galicia_icon))
                .title("Galicia-Capital: Santiago de Compostela").snippet("Habitantes: 2695645")
        )

        val laRioja = LatLng(42.568072855089895, -2.470916178908127)
        mMap.addMarker(
            MarkerOptions().position(laRioja)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.larioja_icon))
                .title("La Rioja-Capital: Logroño").snippet("Habitantes: 319796")
        )

        val madrid = LatLng(40.429642598652, -3.76167856716930)
        mMap.addMarker(
            MarkerOptions().position(madrid)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.madrid_icon))
                .title("Madrid-Capital: Madrid").snippet("Habitantes: 6751251")
        )

        val melilla = LatLng(35.34689811596408, -2.957162284523383)
        mMap.addMarker(
            MarkerOptions().position(melilla)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.melilla_icon))
                .title("Melilla-Capital: Melilla").snippet("Habitantes: 86261")
        )

        val murcia = LatLng(38.088904824462176, -1.4100155858243844)
        mMap.addMarker(
            MarkerOptions().position(murcia)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.murcia_icon))
                .title("Murcia-Capital: Murcia").snippet("Habitantes: 1518486")
        )

        val navarra = LatLng(42.71764719490406, -1.657559057849277)
        mMap.addMarker(
            MarkerOptions().position(navarra)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.navarra_icon))
                .title("Navarra-Capital: Pamplona").snippet("Habitantes: 661537")
        )

        val paisVasco = LatLng(43.11260202399828, -2.594687915428055)
        mMap.addMarker(
            MarkerOptions().position(paisVasco)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.paisvasco_icon))
                .title("País Vasco-Capital: Vitoria").snippet("Habitantes: 2213993")
        )

        val valencia = LatLng(39.515011403926145, -0.6939076854376838)
        mMap.addMarker(
            MarkerOptions().position(valencia)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.valencia_icon))
                .title("Valencia-Capital: Valencia").snippet("Habitantes: 5058138")
        )

        // Desplazo la cámara a la ubicación proporcionada a través del Intent
        val objetivo = LatLng(latitud, longitud)
        val cameraPosition = CameraPosition.Builder().target(objetivo).zoom(7.0f).build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        mMap.moveCamera(cameraUpdate)
    }
}