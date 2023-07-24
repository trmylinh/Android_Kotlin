package com.example.autocompletetextview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.autocompletetextview.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCity = resources.getStringArray(R.array.city)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listCity)

        binding.autoCity.setAdapter(adapter)

        //gợi ý: chỉ cần thay đổi input sẽ hiện gửi ý, không quan tâm bao nhiêu kí tự
        binding.autoCity.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
            if(b){
                binding.autoCity.showDropDown()
            }
        }

        //event
        binding.autoCity.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                // i : vi trị nằm trong list city trong string.xml
                // chứ không phải là vị trí list dropdown của gợi ý
                Toast.makeText(this, "click item ${binding.autoCity.text.toString()}", Toast.LENGTH_SHORT).show()
            }
    }
}