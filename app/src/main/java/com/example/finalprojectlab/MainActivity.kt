package com.example.finalprojectlab

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    enum class LoginSuccess
    constructor(val intValue: Int){

        login(1),
        password(2),
        success(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textUsername = findViewById<TextView>(R.id.txtUsername)
        val textPassword = findViewById<TextView>(R.id.txtPassword)
        val btnLogin = findViewById<Button>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            when (checkLogin(textUsername.text.toString(), textPassword.text.toString())){

                LoginSuccess.login -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessageLogin), Toast.LENGTH_LONG).show()
                    textUsername.requestFocus()
                }

                LoginSuccess.password -> {
                    Toast.makeText(applicationContext, getString(R.string.errMessagePassword), Toast.LENGTH_LONG).show()
                    textPassword.requestFocus()
                }

                else ->
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
            }


        }
    }

    fun checkLogin(textUsername: String, textPassword: String): LoginSuccess{
        val holdLogin = "Maria"
        val holdPass = "password"

        if (holdLogin != textUsername){
            return LoginSuccess.login
        }
        return if (holdPass != textPassword){
            return LoginSuccess.password
        } else LoginSuccess.success
    }
}