package com.example.makebeautybutton_textview_withshapexml

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.makebeautybutton_textview_withshapexml.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}