package com.example.theactivitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.theactivitylifecycle.databinding.ActivityMainBinding
import com.example.theactivitylifecycle.databinding.ActivityScreen2Binding

private lateinit var binding: ActivityScreen2Binding
class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}