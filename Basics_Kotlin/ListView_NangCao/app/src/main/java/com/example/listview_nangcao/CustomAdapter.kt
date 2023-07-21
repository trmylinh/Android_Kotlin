package com.example.listview_nangcao

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(
    val activity: Activity,
    val list : List<DataFilm>
) : ArrayAdapter<DataFilm>(activity, R.layout.list_item){

    // quy dinh ve bao nhieu dong len view
    override fun getCount(): Int {
        // ve len view tat cac cac dong cua list
        return list.size
    }

    // ve nhu the nao
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // view la tat ca nhung gi tren giao dien (text view, button .....)
        // layoutInflater la 1 component giup chuyen file xml thanh view trong android
        val contexts = activity.layoutInflater

        val rowView = contexts.inflate(R.layout.list_item, parent, false)

        val image = rowView.findViewById<ImageView>(R.id.imageView)
        val title = rowView.findViewById<TextView>(R.id.title)
        val desc = rowView.findViewById<TextView>(R.id.desc)

        title.text = list[position].title
        desc.text = list[position].desc
        image.setImageResource(list[position].image)


        return rowView
    }
}