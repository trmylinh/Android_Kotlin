package com.example.optionmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.optionmenu.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    // khoi tao menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuExit -> Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
            R.id.menuAdd -> Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
            R.id.menuHome -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}