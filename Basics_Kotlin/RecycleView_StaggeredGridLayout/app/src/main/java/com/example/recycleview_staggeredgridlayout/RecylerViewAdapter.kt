package com.example.recycleview_staggeredgridlayout

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recycleview_staggeredgridlayout.databinding.LayoutItemBinding

class RecylerViewAdapter(
    val list: List<Int>
) : RecyclerView.Adapter<RecylerViewAdapter.ItemViewHolder>() {
    private lateinit var binding: LayoutItemBinding
    inner class ItemViewHolder(binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context)
        binding  = LayoutItemBinding.inflate(view, p0, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.itemView.apply {
            run{
                binding.imageView.setImageResource(list[p1])
            }
        }
    }
}