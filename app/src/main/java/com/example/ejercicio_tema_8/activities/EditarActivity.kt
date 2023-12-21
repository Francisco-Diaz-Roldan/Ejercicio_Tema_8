package com.example.ejercicio_tema_8.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.ejercicio_tema_8.R
import com.example.ejercicio_tema_8.databinding.ActivityEditarBinding

class EditarActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityEditarBinding
    private lateinit var btnEditar: Button
    private lateinit var btnCancelar: Button
    private lateinit var infoNombre: EditText
    private lateinit var infoImagen: ImageView
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombreComunidad")
        val imagen = intent.getIntExtra("imagen", 0)
        btnEditar = binding.btnEditar
        btnEditar.setOnClickListener(this)
        btnCancelar = binding.btnCancelar
        btnCancelar.setOnClickListener(this)
        infoNombre = binding.etNombre
        infoImagen = binding.imgComunidadAutonoma
        infoImagen.setImageResource(imagen)
        infoNombre.hint = nombre
        id = intent.getIntExtra("id", 0)

    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.btnEditar -> {
                val intent = Intent(this, MainActivity::class.java)
                val name = infoNombre.text.toString()

                if(name != "") {
                    intent.putExtra("nombre", name)
                    intent.putExtra("id", id)
                    setResult(RESULT_OK, intent)
                }
                finish()
            }
            R.id.btnCancelar -> {
                finish()
            }
        }
    }
}