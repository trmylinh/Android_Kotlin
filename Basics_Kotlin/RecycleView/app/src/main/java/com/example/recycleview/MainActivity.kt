package com.example.recycleview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.recycleview.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val list = mutableListOf<Data>()
        list.add(Data(R.drawable.food1, "Food 1", "Delicious"))
        list.add(Data(R.drawable.food10, "Food 2", "Delicious"))
        list.add(Data(R.drawable.food4, "Food 3", "Delicious"))
        list.add(Data(R.drawable.food5, "Food 4", "Delicious"))
        list.add(Data(R.drawable.food7, "Food 5", "Delicious"))
        list.add(Data(R.drawable.food9, "Food 6", "Delicious"))

        val adapter = RecycleViewAdapter(list, object:RecyclerViewInterface{
            override fun onClickItemFood(position: Int) {
                Toast.makeText(this@MainActivity, "You choose ${list[position].name}", Toast.LENGTH_SHORT).show()
            }
        })
        binding.recycleView.adapter = adapter

//        binding.recycleView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.recycleView.layoutManager = GridLayoutManager(
            this,
            2,
            GridLayoutManager.VERTICAL,
            false
        )


    }
}