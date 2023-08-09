package com.example.swipetoshowbutton_recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swipetoshowbutton_recyclerview.databinding.ItemLayoutBinding
import com.example.swipetoshowbutton_recyclerview.model.Item

class MyAdapter(
    val itemList: MutableList<Item>,
    val context: Context
) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    private lateinit var binding: ItemLayoutBinding
    class ViewHolder(binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = ItemLayoutBinding. inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(itemList[position].image).into((binding.cartImage))
        binding.cartItemPrice.text = itemList[position].price
        binding.cartItemName.text = itemList[position].name
    }
}