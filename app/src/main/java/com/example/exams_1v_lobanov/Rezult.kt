package com.example.exams_1v_lobanov

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Rezult : AppCompatActivity() {
    private lateinit var buttonBack:Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rezult)

        buttonBack = findViewById(R.id.buttonBack)
        textViewResult = findViewById(R.id.textViewResult)

        val result = intent.getDoubleExtra("RESULT", 0.0)
        val unitTo = intent.getStringExtra("UNIT_TO")

        textViewResult.text = "Результат: $result $unitTo"

        buttonBack.setOnClickListener{
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}