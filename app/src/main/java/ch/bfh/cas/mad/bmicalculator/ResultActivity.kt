package ch.bfh.cas.mad.bmicalculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val bmi = intent.extras?.getDouble("ch.bfh.cas.mad.bmicalculator.bmi")
            ?: throw Exception("No BMI provided")
        val textViewOutput = findViewById<TextView>(R.id.textview_bmi_output)
        textViewOutput.text = bmi.toString()
    }
}