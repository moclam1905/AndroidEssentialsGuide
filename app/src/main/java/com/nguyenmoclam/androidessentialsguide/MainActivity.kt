package com.nguyenmoclam.androidessentialsguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyenmoclam.androidessentialsguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

    }
}