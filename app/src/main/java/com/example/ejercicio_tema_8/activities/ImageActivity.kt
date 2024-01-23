package com.example.ejercicio_tema_8.activities

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.ejercicio_tema_8.databinding.ActivityImageBinding
import com.example.ejercicio_tema_8.domain.ComunidadAutonomaDAO

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.extras!!.getInt("id")
        val miDAO = ComunidadAutonomaDAO()
        val comunidadAutonoma = miDAO.obtenerComunidadAutonoma(this, id)
        if (comunidadAutonoma != null) {
            if (comunidadAutonoma.uri.isNotEmpty()){
                val uri = Uri.parse(comunidadAutonoma.uri)
                binding.wholeImage.load(uri)
            } else {
                Toast.makeText(this, "${comunidadAutonoma.nombre} no tiene una foto asociada",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}