package com.example.spinner_drop_dropdownmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.spinner_drop_dropdownmenu.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // spinner basic
        setUpSpinner()
    }

    private fun setUpSpinner() {
        // list languages -> su dung resources lay tu string.xml
//        val list = resources.getStringArray(R.array.languages)

        // list thay doi so lieu
        val list = mutableListOf<String>()
        // hoac co the su dung editText de them phan tu -> button add
        list.add("English")
        list.add("Vietnamese")
        //khai bao bo dieu huong -> adapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, // cach hien thi 1 dong
            list
        )

        binding.spLanguage.adapter = adapter

        // event
        binding.spLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // p2: chỉ mục của phần tử trong list
                Toast.makeText(this@MainActivity, "You choose ${list[p2]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}