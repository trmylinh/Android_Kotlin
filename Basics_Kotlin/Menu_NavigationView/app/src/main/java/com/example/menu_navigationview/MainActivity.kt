package com.example.menu_navigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.menu_navigationview.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // su dung mau cho icon menu (khong phai mau mac dinh den - trang)
        binding.navLeftMenu.itemIconTintList = null

        // event
        binding.navLeftMenu.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.nav_message -> Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show()
                R.id.nav_exit -> finish()

            }
            true
        }

    }
}