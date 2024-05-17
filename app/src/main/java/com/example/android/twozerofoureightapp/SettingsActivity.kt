package com.example.android.twozerofoureightapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.twozerofoureight.R
import com.example.android.twozerofoureightapp.MainActivity

class SettingsActivity : AppCompatActivity() {
    private lateinit var back_btn: ImageButton
    private lateinit var img_btn_language: ImageButton
    private lateinit var img_btn_notify: ImageButton
    private lateinit var img_btn_privacy: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        back_btn = findViewById(R.id.back_button)
        img_btn_language = findViewById(R.id.img_btn_language)  // Corrected ID
        img_btn_notify = findViewById(R.id.img_btn_notify)      // Corrected ID
        img_btn_privacy = findViewById(R.id.img_btn_privacy)    // Corrected ID


        back_btn.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
        }

        img_btn_language.setOnClickListener {
            val to_language_act = Intent(this, LanguageActivity::class.java)
            startActivity(to_language_act)
        }
    }
}