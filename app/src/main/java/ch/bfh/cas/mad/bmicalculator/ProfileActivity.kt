package ch.bfh.cas.mad.bmicalculator

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
    private lateinit var buttonPicture: Button
    private lateinit var imageViewProfilePicture: ImageView
    private lateinit var settings: Settings
    private lateinit var picturePreview: ActivityResultLauncher<Void?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        editTextUsername = findViewById(R.id.edittext_username)
        buttonSave = findViewById(R.id.button_save)
        settings = Settings(context = this)
        buttonPicture = findViewById(R.id.button_picture)
        imageViewProfilePicture = findViewById(R.id.imageview_profile_picture)
        picturePreview =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { preview: Bitmap? ->
                imageViewProfilePicture.setImageBitmap(preview)
            }
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
        buttonPicture.setOnClickListener {
            picturePreview.launch(null)
        }
    }

    override fun onPause() {
        super.onPause()
        buttonSave.setOnClickListener(null)
        buttonPicture.setOnClickListener(null)
    }
}