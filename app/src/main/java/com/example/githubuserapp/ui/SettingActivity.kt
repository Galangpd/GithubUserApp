package com.example.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserapp.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar?.hide()
    }
}