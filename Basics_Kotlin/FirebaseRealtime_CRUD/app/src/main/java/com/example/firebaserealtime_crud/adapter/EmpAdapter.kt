package com.example.firebaserealtime_crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtime_crud.EmployeeModel
import com.example.firebaserealtime_crud.R
import com.example.firebaserealtime_crud.databinding.EmpListItemBinding

class EmpAdapter (
    private val ds : ArrayList<EmployeeModel>
): RecyclerView.Adapter<EmpAdapter.ViewHolder>(){
    private lateinit var binding: EmpListItemBinding

    // code Apdater lang nghe su kien
    lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    // tao view holder
//    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    class ViewHolder( binding: EmpListItemBinding, clickListener: onItemClickListener): RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.emp_list_item, parent, false)
//        return ViewHolder(itemView)
        val itemView = LayoutInflater.from(parent.context)
        binding = EmpListItemBinding.inflate(itemView, parent, false)
        return ViewHolder(binding, mListener)
    }

    override fun getItemCount(): Int {
        return ds.size
    }


    // muon hien thi cac gi thi kha bao trong onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            binding.tvEmpName.text = ds[position].empName
        }
    }
}