package com.example.listview2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.listview2.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    // khai bao 1 list rong
    var list : MutableList<String> = mutableListOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // show list
        showListView()

        addEvent()

    }

    private fun showListView() {
        binding.lvPhone.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            list
        )
    }

    private fun addEvent() {
        // button Save
        binding.btnSave.setOnClickListener {
            handleClickBtn()
        }
    }

    private fun handleClickBtn() {
        var result : String = binding.edtPhone.text.toString()
        list.add(result)

        binding.edtPhone.requestFocus()
        
        binding.edtPhone.setText("")
        showListView()

    }
}