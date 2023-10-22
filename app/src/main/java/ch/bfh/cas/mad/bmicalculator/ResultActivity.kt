package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val bmiKey = "ch.bfh.cas.mad.bmicalculator.bmi"
        fun start(context: Context, bmi: Double) {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(bmiKey, bmi)
            context.startActivity(intent)
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

    private lateinit var buttonBack: Button
    private lateinit var textViewOutput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        buttonBack = findViewById(R.id.button_back)
        textViewOutput = findViewById<TextView>(R.id.textview_bmi_output)
        val bmi = intent.getBmi() ?: throw Exception("No BMI provided")
        textViewOutput.text = bmi.toString()
    }

    override fun onResume() {
        super.onResume()
        buttonBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        buttonBack.setOnClickListener(null)
    }
}