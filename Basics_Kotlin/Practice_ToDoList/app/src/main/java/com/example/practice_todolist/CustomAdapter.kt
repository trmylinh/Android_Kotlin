package com.example.practice_todolist

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(
    val activity: Activity,
    val list: List<DataTask>
) : ArrayAdapter<DataTask>(activity, R.layout.list_item){
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView = context.inflate(R.layout.list_item, parent, false)
        val stt = rowView.findViewById<TextView>(R.id.txtSTT)
        val name = rowView.findViewById<TextView>(R.id.txtName)
        val status = rowView.findViewById<TextView>(R.id.txtStatus)

        if((position + 1) < 10){
            stt.text = "0${(position + 1)}"
        }
        else {
            stt.text = "${(position + 1)}"
        }

        name.text = list[position].name
        if(list[position].status){
            status.text = "Complete"
        } else{
            status.text = "Incomplete"
        }

        return rowView
    }
}