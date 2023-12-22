package com.example.ejercicio_tema_8.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ejercicio_tema_8.R
import com.example.ejercicio_tema_8.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Thread.sleep(500)
        splashScreen.setKeepOnScreenCondition{false}
        prefs = getSharedPreferences("app", MODE_PRIVATE)
        establecerValoresSiExisten()
        binding.btnLogin.setOnClickListener{
            val email=binding.etEmail.text.toString()
            val password= binding.etPassword.text.toString()
            if(login(email, password)) goToMain()
            guardarPreferencias(email, password)
        }

        binding.motionLayout.setTransitionListener(object: MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                binding.motionLayout.visibility = View.GONE
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })
    }

    private fun login(email: String, password: String): Boolean {
        var valido = false
        if(!eMailValido(email)){
            Toast.makeText(this,
                "E-mail inválido, por favor, introduce un email válido",
                Toast.LENGTH_SHORT).show()
        } else if (!passwordValida(password)){
            Toast.makeText(this,
                "Contraseña inválida, por favor, introduce una contraseña de, al menos," +
                        " 5 caracteres", Toast.LENGTH_SHORT).show()
            }else{
                valido = true
        }
        return valido
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        //Evita que pasemos de nuevo a la activity login
        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun guardarPreferencias(email: String, password: String){
        val editor = prefs!!.edit()
        if (binding.swRecordarUsuario.isChecked){
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("recordar", true)
            editor.apply()
        } else{
            editor.clear()
            editor.putBoolean("recordar", false)
            editor.apply()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun establecerValoresSiExisten() {
    val email = prefs.getString("email", "")
    val password = prefs.getString("password", "")
    val recordar = prefs.getBoolean("recordar", false)
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
            binding.swRecordarUsuario.isChecked = recordar
        }
    }

    private fun eMailValido(email:String) : Boolean{
        return  !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun passwordValida(password:String) : Boolean{
        return  !TextUtils.isEmpty(password) && password.length >= 5
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnEditar -> {
                if (eMailValido("paquillo@gmail.com") && passwordValida("12345")){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    println("Por favor, introduce un email y contraseña válidas")
                }
            }
        }
    }
}