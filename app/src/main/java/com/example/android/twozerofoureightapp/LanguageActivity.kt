package com.example.android.twozerofoureightapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.ComponentActivity
import android.widget.ImageView
import com.example.twozerofoureight.R

class LanguageActivity : ComponentActivity() {
    private lateinit var back_btn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        back_btn = findViewById(R.id.back_button)

        // Initialize spinner
        val languageSpinner: Spinner = findViewById(R.id.language_spinner)

        // Create adapter for spinner
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this, R.array.languages_array, android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set adapter to spinner
        languageSpinner.adapter = adapter

        // Initialize confirm button
        val confirmButton: Button = findViewById(R.id.confirm_button)

        back_btn.setOnClickListener {
            val back = Intent(this@LanguageActivity, SettingsActivity::class.java)
            startActivity(back)
        }

        // Set click listener for confirm button
        confirmButton.setOnClickListener {
            // Get selected language from spinner
            val selectedLanguage = languageSpinner.selectedItem as String
            // Perform actions based on selected language
        }
    }
}