package com.example.swipe_items_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swipe_items_recyclerview.databinding.ItemLayoutBinding

class ItemRecyclerAdapter(
    val list: List<String>,
    val context: Context
) : RecyclerView.Adapter<ItemRecyclerAdapter.ViewHolder>(){
    private lateinit var binding: ItemLayoutBinding
    class ViewHolder(binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = ItemLayoutBinding. inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.textView.text = list[position]
    }
}