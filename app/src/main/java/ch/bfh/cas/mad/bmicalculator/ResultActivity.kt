package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val bmiKey = "ch.bfh.cas.mad.bmicalculator.bmi"
        fun start(context: Context, bmi: Double) {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(bmiKey, bmi)
        }

        fun Intent.getBmi(): Double? =
            extras?.let {
                if (!it.containsKey(bmiKey)) {
                    null
                } else {
                    it.getDouble(bmiKey)
                }
            }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val bmi = intent.getBmi() ?: throw Exception("No BMI provided")
        val textViewOutput = findViewById<TextView>(R.id.textview_bmi_output)
        textViewOutput.text = bmi.toString()
    }
}