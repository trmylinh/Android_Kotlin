package com.example.practice_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.practice_todolist.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var itemList = ArrayList<String>()
    private var fileHelper = FileHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList = fileHelper.readData(this)

        var arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itemList
        )
        Log.d("itemList", itemList.toString())
        binding.lvTask.adapter = arrayAdapter

        binding.btnAdd.setOnClickListener {
            var itemTask = binding.edtInput.text.toString()
            itemList.add(itemTask)
            binding.edtInput.setText("")

            fileHelper.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }

    }
}