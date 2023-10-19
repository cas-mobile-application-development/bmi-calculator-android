package ch.bfh.cas.mad.bmicalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private lateinit var editTextHeightInCm: EditText
    private lateinit var editTextWeightInKg: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextHeightInCm = findViewById(R.id.edittext_height_in_cm)
        editTextWeightInKg = findViewById(R.id.edittext_weight_in_kg)
        buttonCalculate = findViewById(R.id.button_calculate)
        textViewOutput = findViewById(R.id.textview_output)
    }

    override fun onResume() {
        super.onResume()
        buttonCalculate.setOnClickListener { calculateBmi() }
    }

    override fun onPause() {
        super.onPause()
        buttonCalculate.setOnClickListener(null)
    }

    private fun calculateBmi() {
        val heightInCm = parseInt(editTextHeightInCm.text.toString())
        val weightInKg = parseInt(editTextWeightInKg.text.toString())
        val heightInMeters = heightInCm / 100.0
        val bmi = weightInKg / (heightInMeters * heightInMeters)
        textViewOutput.text = bmi.toString()
    }
}