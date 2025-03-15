package com.example.exams_1v_lobanov

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Calculator : AppCompatActivity() {
    private lateinit var buttonRez: Button
    private lateinit var spinnerIn: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var inputText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)

        buttonRez = findViewById(R.id.buttonRaschet)
        spinnerIn = findViewById(R.id.spinnerIn)
        spinnerTo = findViewById(R.id.spinnerTo)
        inputText = findViewById(R.id.editInputText)

        buttonRez.setOnClickListener {
            if (inputText.text.isNotEmpty()) {
                val unitFrom = spinnerIn.selectedItem.toString()
                val unitTo = spinnerTo.selectedItem.toString()

                val inputValue = inputText.text.toString().toDouble()

                val result = Conver(inputValue, unitFrom, unitTo)

                val intent = Intent(this, Rezult::class.java)
                intent.putExtra("RESULT", result)
                intent.putExtra("UNIT_TO", unitTo)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Вы не ввели число", Toast.LENGTH_SHORT).show()
            }
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun Conver(value: Double, fromUnit: String, toUnit: String): Double {
        val valueInBytes = when (fromUnit) {
            "Байт" -> value
            "Килобайт" -> value * 1024
            "Мегабайт" -> value * 1024 * 1024
            "Гигабайт" -> value * 1024 * 1024 * 1024
            else -> 0.0
        }

        return when (toUnit) {
            "Байт" -> valueInBytes
            "Килобайт" -> valueInBytes / 1024
            "Мегабайт" -> valueInBytes / (1024 * 1024)
            "Гигабайт" -> valueInBytes / (1024 * 1024 * 1024)
            else -> 0.0
        }
    }
}