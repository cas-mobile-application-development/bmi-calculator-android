package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    private lateinit var recyclerViewInterpretations: RecyclerView
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val viewModelProvider = ViewModelProvider(
            this,
            ResultViewModelFactory(BmiInterpretationsRepository())
        )
        viewModel = viewModelProvider.get(ResultViewModel::class.java)
        buttonBack = findViewById(R.id.button_back)
        textViewOutput = findViewById(R.id.textview_bmi_output)
        recyclerViewInterpretations = findViewById(R.id.recyclerview_interpretations)
        val bmi = intent.getBmi() ?: throw Exception("No BMI provided")
        textViewOutput.text = bmi.toString()

        recyclerViewInterpretations.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.interpretations.collectLatest { interpretations ->
                    val adapter = BmiInterpretationsAdapter(data = interpretations)
                    recyclerViewInterpretations.adapter = adapter
                }
            }
        }
        viewModel.getBmiInterpretaions()
    }

    override fun onResume() {
        super.onResume()
        buttonBack.setOnClickListener {
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        buttonBack.setOnClickListener(null)
    }
}