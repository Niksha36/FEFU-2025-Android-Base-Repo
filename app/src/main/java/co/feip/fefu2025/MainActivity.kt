package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import co.feip.fefu2025.ui.Constants.programmingLanguages
import co.feip.fefu2025.ui.CustomLayout
import co.feip.fefu2025.ui.CustomProgLanguageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.addNewLanguage)
        button.setOnClickListener {
            addCustomProgrammingLanguage()
        }
    }

    private fun addCustomProgrammingLanguage() {
        val customLayout = findViewById<CustomLayout>(R.id.customLayout)
        val newLanguageView = CustomProgLanguageView(this).apply {
            setItem(
                programmingLanguages.random(), android.graphics.Color.argb(
                    255,
                    Random.nextInt(256),
                    Random.nextInt(256),
                    Random.nextInt(256)
                ), Random.nextFloat() * 100
            )
        }
        customLayout.addView(newLanguageView)
    }
}