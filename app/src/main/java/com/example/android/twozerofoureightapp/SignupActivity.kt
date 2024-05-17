package com.example.android.twozerofoureightapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity // Changed from ComponentActivity to AppCompatActivity
import com.example.twozerofoureight.R

class SignupActivity : AppCompatActivity() {
    // Declare variables
    private lateinit var setting_btn: ImageView
    private lateinit var show_btn: ImageView
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var verify: EditText
    private lateinit var register: Button
    private lateinit var login_here: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize views
        setting_btn = findViewById(R.id.setting_button)
        show_btn = findViewById(R.id.action_image)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        verify = findViewById(R.id.verify)
        register = findViewById(R.id.register)
        login_here = findViewById(R.id.login_here)

        // Set click listener for "Login here" TextView
        login_here.setOnClickListener {
            val to_login_act = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(to_login_act)
        }

        // Set click listener for settings button
        setting_btn.setOnClickListener {
            val to_settings_act = Intent(this@SignupActivity, SettingsActivity::class.java)
            startActivity(to_settings_act)
        }

        // Set click listener for register button
        register.setOnClickListener {
            performRegistration()
        }
    }

    private fun performRegistration() {
        val usernameStr = username.text.toString()
        val passwordStr = password.text.toString()
        val verifyStr = verify.text.toString()

        if (usernameStr.isEmpty() || passwordStr.isEmpty() || verifyStr.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (passwordStr != verifyStr) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // Save user credentials
        saveUserCredentials(usernameStr, passwordStr)
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

        // Redirect to login activity
        val toLoginAct = Intent(this@SignupActivity, LoginActivity::class.java)
        startActivity(toLoginAct)
        finish() // Close the signup activity so user can't go back to it
    }

    private fun saveUserCredentials(username: String, password: String) {
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("username", username)
            putString("password", password)
            apply()
        }
    }
}
