package com.example.customspinner

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomSpinnerClass(
    val activity: Activity,
    val listFood : List<DataFood>,
) : ArrayAdapter<DataFood>(activity, R.layout.list_food){
    override fun getCount(): Int {
        return listFood.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //convertView: View? chế độ xem cho từng mục nằm trong spinner
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    //xu ly view
    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View{
        val contexts = activity.layoutInflater

        // xml -> view
        val rowView = contexts.inflate(R.layout.list_food, parent, false)

        val image = rowView.findViewById<ImageView>(R.id.imageView)
        val name = rowView.findViewById<TextView>(R.id.txtName)

        image.setImageResource(listFood[position].image)
        name.text=listFood[position].name

        return rowView
    }
}