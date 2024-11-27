package com.example.sportlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            // Navegar para a tela principal (Home)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Fecha a tela de login
        }
    }
}
