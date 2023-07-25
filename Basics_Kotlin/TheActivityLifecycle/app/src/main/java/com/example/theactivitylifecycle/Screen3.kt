package com.example.theactivitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.theactivitylifecycle.databinding.ActivityScreen3Binding

private lateinit var binding: ActivityScreen3Binding
class Screen3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}