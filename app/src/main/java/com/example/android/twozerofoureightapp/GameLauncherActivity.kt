package com.example.android.twozerofoureightapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.twozerofoureight.R
import com.google.android.material.tabs.TabLayout
import com.example.android.twozerofoureightapp.GamePagerAdapter

class GameLauncherActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var settingButton: ImageView
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamelaucher)

        backButton = findViewById(R.id.back_button)
        settingButton = findViewById(R.id.setting_button)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        backButton.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

        settingButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Set up ViewPager with adapter
        val adapter = GamePagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}

