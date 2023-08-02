package com.example.intent

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.intent.databinding.ActivityScreen2Binding
import com.example.intent.databinding.ActivityScreen3Binding

private lateinit var binding: ActivityScreen3Binding
class Screen3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // nhan du lieu tu Bundle cua Screen2
        val intentGet = intent

        // lay bundle ra khoi Intent
        val bundle = intentGet.extras
        if(bundle != null){
            val bienString = bundle.getString("bienString")
            val bienDouble = bundle.getDouble("bienDouble")
            val bienBoolean = bundle.getBoolean("bienBoolean")
            val bienInt = bundle.getInt("bienInt")

            binding.txtResult.text = "Result Bundle Screen2: \n$bienString\n$bienDouble\n$bienBoolean\n$bienInt"
        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, Screen2::class.java)
            startActivity(intent)
        }
    }
}