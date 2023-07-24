package com.example.gridview

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomGridView(
    val activity: Activity,
    val list: List<DataFilm>
) : ArrayAdapter<DataFilm>(activity, R.layout.layout_item) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater

        val rowView = context.inflate(R.layout.layout_item, parent, false)

        val image = rowView.findViewById<ImageView>(R.id.imageView)
        val name = rowView.findViewById<TextView>(R.id.txtNameFilm)

        image.setImageResource(list[position].image)
        name.text=list[position].name

        return rowView
    }
}