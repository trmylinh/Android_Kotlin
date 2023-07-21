package com.example.listview

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.listview.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set background cho list view
        val color = Color.parseColor("#FFBBFF")
        binding.lvCountry.setBackgroundColor(color)

        addEvents()

    }

    private fun addEvents() {
        showListView()
    }

    private fun showListView() {
        // string.xml luư nguồn dữ liệu không đổi, như thứ trong tuần, tháng trong năm ....
        // sử dụng resources để lấy thông tin từ string.xml
        var arrCountry = resources.getStringArray(R.array.arrCountry)

        // gan nguon cho apdater
        binding.lvCountry.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrCountry)


        binding.lvCountry.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(
                this,
                "You click ${arrCountry[i]}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}