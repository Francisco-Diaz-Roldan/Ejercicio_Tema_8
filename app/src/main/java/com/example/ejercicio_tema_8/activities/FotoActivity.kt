package com.example.ejercicio_tema_8.activities

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ejercicio_tema_8.databinding.ActivityFotoBinding
import com.example.ejercicio_tema_8.domain.ComunidadAutonomaDAO
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FotoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFotoBinding
    private lateinit var cameraExecutorService: ExecutorService
    private lateinit var imageCapture: ImageCapture
    private lateinit var nombreComunidad: String

    private var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.extras!!.getInt("id")
        nombreComunidad = intent.extras!!.getString("nombreComunidad").toString()

        //Solicitud de los permisos de la cámara
        if(allPermissionsGranted()){
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
        //Se establece el listener para la captura de la foto
        binding.btnHacerFoto.setOnClickListener{hacerFoto()}
        cameraExecutorService = Executors.newSingleThreadExecutor()
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all{
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun hacerFoto(){
        // Obtenemos una referencia estable para el caso de uso de captura de imágenes
        val imageCapture = imageCapture?: return
        // Asigno la instancia local a la propiedad de clase
        this@FotoActivity.imageCapture = imageCapture
        // Creamos un nombre para el archivo con la hora y dónde se va a almacenar
        val nombreComunidad = "${nombreComunidad}_" + SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, nombreComunidad)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()
        // Se establece el listener para la captura de imagen que se lanzará cuando se ha hecho una foto
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(ex: ImageCaptureException) {
                    Log.e(TAG, "Fallo en la captura de la foto: ${ex.message}", ex)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val msg = "Captura de foto correcta: ${output.savedUri}"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    // INSERTAR LA URI EN LA BASE DE DATOS output.savedUri
                    val fotoUri = output.savedUri
                    val miDAO = ComunidadAutonomaDAO()
                    val comunidadAutonomaElegida = miDAO.obtenerComunidadAutonoma(applicationContext, id)
                    comunidadAutonomaElegida?.uri = fotoUri?.toString().toString()
                    comunidadAutonomaElegida?.let { miDAO.actualizarBBDD(applicationContext, it) }
                    if (comunidadAutonomaElegida != null) { //Actualizo el DAO -> Importante
                        miDAO.actualizarBBDD(applicationContext, comunidadAutonomaElegida)
                    }
                }
            }
        )
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            // Vinculamos para vincular el ciclo de vida de la cámara al ciclo de vida de la app
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            //Preview
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.cameraViewFinder.surfaceProvider)
            }
            //Selecciona la cámara trasera por defecto
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                //Desvincula antes de volver a vincular
                cameraProvider.unbindAll()
                //Vinculamos los casos de uso a la cámara
                imageCapture = ImageCapture.Builder().build() // Inicializa ImageCapture
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (ex: Exception) {
                Log.e(TAG, "Vinculación errónea", ex)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    override fun onDestroy(){
        super.onDestroy()
        cameraExecutorService.shutdown()
    }

    companion object{
        private  const val TAG = "Ejercicio_Tema_8"
        private  const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private  const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA
            ).apply{
                if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_PERMISSIONS){
            if (allPermissionsGranted()){
                startCamera()
            } else{
                Toast.makeText(this,
                    "Permisos no concedidos por el usuario.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}