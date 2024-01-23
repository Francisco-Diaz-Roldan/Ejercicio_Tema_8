package com.example.ejercicio_tema_8.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ejercicio_tema_8.databinding.ActivityFotoBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FotoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFotoBinding
    private lateinit var cameraExecutorService: ExecutorService
    private lateinit var imageCapture: ImageCapture
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun hacerFoto(){}

    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            // Vinculamos para vincular el ciclo de vida de la cámara al ciclo de vida de la app
            val cameraProvider : ProcessCameraProvider = cameraProviderFuture.get()
            //Preview
            val preview = Preview.Builder()
                .build()
                .also{
                    it.setSurfaceProvider(binding.cameraViewFinder.surfaceProvider)
                }
            //Selecciona la cámara trasera por defecto
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try{
                //Desvincula antes de volver a vincular
                cameraProvider.unbindAll()
                //Vinculamos los casos de uso a la cámara
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview)
            } catch (ex : Exception){
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

