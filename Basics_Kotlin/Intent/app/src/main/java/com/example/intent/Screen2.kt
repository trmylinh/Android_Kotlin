package com.example.intent

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.intent.databinding.ActivityMainBinding
import com.example.intent.databinding.ActivityScreen2Binding

private lateinit var binding: ActivityScreen2Binding
class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // nhan du lieu tu Intent cua MainActivity
        val intentGet = intent
        val bienStringGet = intentGet.getStringExtra("bienString")
        val bienDoubleGet = intentGet.getDoubleExtra("bienDouble", 0.0)
        val bienBooleanGet = intentGet.getBooleanExtra("bienBoolean", false)

        binding.txtResult.text = "Result Intent MainActivity: \n$bienStringGet \n$bienDoubleGet \n$bienBooleanGet"


        //back to screen Main
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnGotoScreen3.setOnClickListener {
            val intent = Intent(this, Screen3::class.java)

            // truyen du lieu sang Screen 3 voi Bundle
            val bundle = Bundle()
            bundle.putString("bienString", "Tran My Linh")
            bundle.putDouble("bienDouble", 1.7)
            bundle.putBoolean("bienBoolean", true)
            bundle.putInt("bienInt", 2002)

            // dat Bundle vao trong Intent
            intent.putExtras(bundle)

            startActivity(intent)
        }





    }
}