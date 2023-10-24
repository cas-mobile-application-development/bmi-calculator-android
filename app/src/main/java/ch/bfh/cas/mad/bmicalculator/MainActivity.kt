package ch.bfh.cas.mad.bmicalculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.lang.Integer.parseInt

class MainActivity : AppCompatActivity() {
    private lateinit var editTextHeightInCm: EditText
    private lateinit var editTextWeightInKg: EditText
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))
        title = getString(R.string.app_title)
        editTextHeightInCm = findViewById(R.id.edittext_height_in_cm)
        editTextWeightInKg = findViewById(R.id.edittext_weight_in_kg)
        buttonCalculate = findViewById(R.id.button_calculate)
    }

    override fun onResume() {
        super.onResume()
        buttonCalculate.setOnClickListener { calculateBmi() }
    }

    override fun onPause() {
        super.onPause()
        buttonCalculate.setOnClickListener(null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.main_menu_profile) {
            ProfileActivity.start(context = this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun calculateBmi() {
        val heightInCm = parseInt(editTextHeightInCm.text.toString())
        val weightInKg = parseInt(editTextWeightInKg.text.toString())
        val heightInMeters = heightInCm / 100.0
        val bmi = weightInKg / (heightInMeters * heightInMeters)
        ResultActivity.start(context = this, bmi = bmi)
    }
}