package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ProfileActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var editTextUsername: EditText
    private lateinit var buttonSave: Button
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        editTextUsername = findViewById(R.id.edittext_username)
        buttonSave = findViewById(R.id.button_save)
        settings = Settings(context = this)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            settings.getUsername().mapNotNull { it }.collectLatest { username ->
                editTextUsername.setText(username)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        buttonSave.setOnClickListener {
            lifecycleScope.launch {
                settings.setUsername(editTextUsername.text.toString())
                buttonSave.text = getString(R.string.saved)
                buttonSave.postDelayed({ buttonSave.text = getString(R.string.save)}, 3000)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        buttonSave.setOnClickListener(null)
    }
}