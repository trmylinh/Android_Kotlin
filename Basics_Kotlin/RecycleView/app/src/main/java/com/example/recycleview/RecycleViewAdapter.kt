package com.example.recycleview

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recycleview.databinding.LayoutItemBinding

// Class RecycleViewAdapter là một lớp được định nghĩa để làm adapter cho RecyclerView.
// kế thừa từ RecyclerView.Adapter và sử dụng generic type FoodViewHolder để chỉ định lớp ViewHolder sẽ được sử dụng
class RecycleViewAdapter(
    val list: List<Data>,
    val onClickItemFood : RecyclerViewInterface,
) : RecyclerView.Adapter<RecycleViewAdapter.FoodViewHolder>() {
    private lateinit var binding: LayoutItemBinding
    // class viewholder
    inner class FoodViewHolder(binding: LayoutItemBinding) : RecyclerView.ViewHolder( binding.root)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FoodViewHolder {
        val view = LayoutInflater.from(p0.context)
        binding = LayoutItemBinding.inflate(view, p0, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // Lớp FoodViewHolder được sử dụng để giữ tham chiếu đến các thành phần trong LayoutItemBinding
    // bao gồm txtDesc txtName imgView
    // Việc khởi tạo lớp FoodViewHolder là để tạo ra 1 instance của ViewHolder
    // có thể giữ các tham chiếu đến các thành phần giao diện trong item layout và nhận dữ liệu để hiển thị
    // tránh việc tìm kiếm và tham chiếu lại các thành phần giao diện mỗi khi item được hiển thị lại
    // giúp cải thiện hiệu suất và tối ưu hóa việc hiển thị danh sách trong RecyclerView
    override fun onBindViewHolder(p0: FoodViewHolder, p1: Int) {
        p0.itemView.apply {
            run {
                binding.txtDesc.text = list[p1].desc
                binding.txtName.text = list[p1].name
                binding.imgView.setImageResource(list[p1].image)
            }

            // lang nghe onClick
            p0.itemView.setOnClickListener {
                onClickItemFood.onClickItemFood(p1)
            }
        }
    }
}