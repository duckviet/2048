package com.example.android.twozerofoureightapp
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.twozerofoureight.R

class LoginActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var back_btn: ImageView
    private lateinit var setting_btn: ImageButton
    private lateinit var show_btn: ImageButton
    private lateinit var username_edt: EditText
    private lateinit var passwd_edt: EditText
    private lateinit var remember_cb: CheckBox
    private lateinit var login_btn: Button
    private lateinit var signup_tv: TextView
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        back_btn = findViewById(R.id.back_button)
        setting_btn = findViewById(R.id.setting_button)
        show_btn = findViewById(R.id.show)
        username_edt = findViewById(R.id.username)
        passwd_edt = findViewById(R.id.password)
        remember_cb = findViewById(R.id.remember)
        login_btn = findViewById(R.id.login)
        signup_tv = findViewById(R.id.signup)

        // Set click listener for back button
        back_btn.setOnClickListener {
            val back = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(back)
        }

        // Set click listener for show button
        show_btn.setOnClickListener {
            togglePasswordVisibility()
        }

        // Set click listener for "Sign up here" TextView
        signup_tv.setOnClickListener {
            val to_signup_act = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(to_signup_act)
        }

        // Set click listener for settings button
        setting_btn.setOnClickListener {
            val to_settings_act = Intent(this@LoginActivity, SettingsActivity::class.java)
            startActivity(to_settings_act)
        }

        // Set click listener for login button
        login_btn.setOnClickListener {
            performLogin()
        }
    }

    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            val showImg: Drawable? = getDrawable(R.drawable.show_btn)
            show_btn.setImageDrawable(showImg)
            passwd_edt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            val unshowImg: Drawable? = getDrawable(R.drawable.show_btn_2)
            show_btn.setImageDrawable(unshowImg)
            passwd_edt.inputType = InputType.TYPE_CLASS_TEXT
        }
        isPasswordVisible = !isPasswordVisible
        // Move the cursor to the end of the text
        passwd_edt.setSelection(passwd_edt.text.length)
    }

    private fun performLogin() {
        val username = username_edt.text.toString()
        val password = passwd_edt.text.toString()
        val rememberMe = remember_cb.isChecked

        if (username.isNotEmpty() && password.isNotEmpty()) {
            // Retrieve stored credentials
            val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val storedUsername = sharedPref.getString("username", null)
            val storedPassword = sharedPref.getString("password", null)

            if (username == storedUsername && password == storedPassword) {
                if (rememberMe) {
                    saveUserCredentials(username, password)
                }
                val toMainAct = Intent(this@LoginActivity, GameLauncherActivity::class.java)
                startActivity(toMainAct)
                finish() // Close the login activity so user can't go back to it
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
        }
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
