package com.example.recycleview_staggeredgridlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.recycleview_staggeredgridlayout.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleShow()

    }

    private fun handleShow() {
        val list = mutableListOf<Int>()
        list.add(R.drawable.hinh1)
        list.add(R.drawable.hinh2)
        list.add(R.drawable.hinh3)
        list.add(R.drawable.hinh4)
        list.add(R.drawable.hinh5)
        list.add(R.drawable.hinh3)
        list.add(R.drawable.hinh4)
        list.add(R.drawable.hinh5)
        list.add(R.drawable.hinh1)
        list.add(R.drawable.hinh2)

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )

        val itemAdapter = RecylerViewAdapter(list)
        binding.recyclerView.adapter = itemAdapter
    }
}