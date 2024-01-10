package com.example.ejercicio_tema_8.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio_tema_8.R
import com.example.ejercicio_tema_8.adapter.ComunidadAutonomaAdapter
import com.example.ejercicio_tema_8.databinding.ActivityMainBinding
import com.example.ejercicio_tema_8.domain.ComunidadAutonoma
import com.example.ejercicio_tema_8.domain.ComunidadAutonomaDAO
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var comunidadDAO: ComunidadAutonomaDAO = ComunidadAutonomaDAO()
    private lateinit var comunidadSeleccionada: ComunidadAutonoma
    private lateinit var listaComunidades: List<ComunidadAutonoma>
    private lateinit var binding: ActivityMainBinding
    private lateinit var intentLaunch: ActivityResultLauncher<Intent>
    private var nombreComunidad = "Sin nombre"
    private var id: Int = 0
    private lateinit var adapter: ComunidadAutonomaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listaComunidades = comunidadDAO.cargarLista(this)
        binding.rvComunidades.layoutManager = LinearLayoutManager(this)
        binding.rvComunidades.adapter =
            ComunidadAutonomaAdapter(listaComunidades) { comunidadAutonoma ->
                onItemSelected(comunidadAutonoma)
            }

        intentLaunch = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                nombreComunidad = result.data?.extras?.getString("nombre").toString()
                id = result.data?.extras?.getInt("id") as Int
                listaComunidades[id].nombre = nombreComunidad
                adapter = ComunidadAutonomaAdapter(listaComunidades) { comunidadAutonoma ->
                    onItemSelected(comunidadAutonoma)
                }
                adapter.notifyItemChanged(id)
                comunidadDAO.actualizarBBDD(this, listaComunidades[id])
                binding.rvComunidades.adapter = adapter
            }
        }

        this.onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable, menu)
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.limpiar -> {
                comunidadDAO.cambiarEstadoEliminado(this)
                listaComunidades = comunidadDAO.cargarLista(this)
                binding.rvComunidades.adapter?.notifyDataSetChanged()
                binding.rvComunidades.adapter = ComunidadAutonomaAdapter(listaComunidades) {
                    onItemSelected(it)
                }
                true
            }

            R.id.recargar -> {
                comunidadDAO.cambiarEstadoActivo(this)
                listaComunidades = comunidadDAO.cargarLista(this)
                binding.rvComunidades.adapter?.notifyDataSetChanged()
                binding.rvComunidades.adapter = ComunidadAutonomaAdapter(listaComunidades) {
                    onItemSelected(it)
                }
                true
            }

            R.id.logOut -> {
                val intent = Intent(this, LoginActivity::class.java)
                // Limpiola pila de actividades y coloco la actividad de inicio de sesión en la parte superior
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        lateinit var miIntent: Intent
        comunidadSeleccionada = listaComunidades[item.groupId]
        when (item.itemId) {
            0 -> {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Eliminar ${comunidadSeleccionada.nombre}")
                    .setMessage("¿Estás seguro de que quieres eliminar ${comunidadSeleccionada.nombre}?")
                    .setNeutralButton("Cerrar", null)
                    .setPositiveButton("Aceptar") { _, _ ->
                        display("Se ha eliminado ${comunidadSeleccionada.nombre}")
                        val updatedList = listaComunidades.toMutableList()
                        updatedList.remove(comunidadSeleccionada)
                        listaComunidades = updatedList.toList()
                        adapter.updateList(listaComunidades)
                        comunidadDAO.borrarDeBBDD(this, comunidadSeleccionada.nombre)
                    }
                    .create()
                alert.show()
            }
            1 -> {
                miIntent = Intent(this, EditarActivity::class.java)
                miIntent.putExtra("nombreComunidad", listaComunidades[item.groupId].nombre)
                miIntent.putExtra("id", item.groupId)
                miIntent.putExtra("imagen", listaComunidades[item.groupId].imagen)
                intentLaunch.launch(miIntent)
            }
            else -> return super.onContextItemSelected(item)
        }
        return true
    }


    private fun display(s: String) {
        Snackbar.make(binding.root, s, Snackbar.LENGTH_SHORT).show()
    }

    private fun onItemSelected(comunidadAutonoma: ComunidadAutonoma) {
        Toast.makeText(this, "Yo soy de ${comunidadAutonoma.nombre}", Toast.LENGTH_SHORT).show()
        // Para iniciar la actividad OpenStreetMapsActivity o en GoogleMapsActivity

        val intent = Intent(this, GoogleMapsActivity::class.java)
        intent.putExtra("comunidadNombre", comunidadAutonoma.nombre)
        intent.putExtra("comunidadHabitantes", comunidadAutonoma.habitantes)
        intent.putExtra("comunidadCapital", comunidadAutonoma.capital)
        intent.putExtra("comunidadLatitud", comunidadAutonoma.latitud)
        intent.putExtra("comunidadLongitud", comunidadAutonoma.longitud)
        startActivity(intent)
    }

}

/*
override fun onContextItemSelected(item: MenuItem): Boolean {
        lateinit var miIntent: Intent
        comunidadSeleccionada = listaComunidades[item.groupId]
        when (item.itemId) {

            0 -> {
                val alert =
                    AlertDialog.Builder(this)
                    .setTitle("Eliminar ${comunidadSeleccionada.nombre}")
                    .setMessage("¿Estás seguro de que quieres eliminar ${comunidadSeleccionada.nombre}?")
                    .setNeutralButton("Cerrar", null)
                    .setPositiveButton("Aceptar") { _, _ ->
                            display("Se ha eliminado ${comunidadSeleccionada.nombre}")

                            // Actualizo la lista quitando el elemento
                            listaComunidades = listaComunidades.minus(comunidadSeleccionada)
                            adapter.updateList(listaComunidades)
                            binding.rvComunidades.adapter?.notifyItemRemoved(item.groupId)
                            binding.rvComunidades.adapter?.notifyItemRangeChanged(
                                item.groupId,
                                listaComunidades.size
                            )
                            binding.rvComunidades.adapter =
                                ComunidadAutonomaAdapter(listaComunidades) {
                                    onItemSelected(it)
                                }
                            comunidadDAO.borrarDeBBDD(this, comunidadSeleccionada.nombre)
                        }.create()
                alert.show()
            }

            1 -> {
                miIntent = Intent(this, EditarActivity::class.java)
                miIntent.putExtra("nombreComunidad", listaComunidades[item.groupId].nombre)
                miIntent.putExtra("id", item.groupId)
                miIntent.putExtra("imagen", listaComunidades[item.groupId].imagen)
                intentLaunch.launch(miIntent)
            }

            else -> return super.onContextItemSelected(item)
        }
        return true
    }
*/