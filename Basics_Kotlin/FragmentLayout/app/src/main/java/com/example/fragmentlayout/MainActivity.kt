package com.example.fragmentlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentlayout.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sub1 = Fragment1()
        val sub2 = Fragment2()

        binding.btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout1, sub1)
                // ap dung
                commit()
            }
        }

        binding.btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout1, sub2)
                // ap dung
                commit()
            }
        }


    }
}