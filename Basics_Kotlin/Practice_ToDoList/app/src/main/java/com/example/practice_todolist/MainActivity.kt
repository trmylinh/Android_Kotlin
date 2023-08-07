package com.example.practice_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practice_todolist.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var itemList = ArrayList<DataTask>()
    private var fileHelper = FileHelper()
//    private lateinit var arrayAdapter: ArrayAdapter<Any>
    private lateinit var arrayAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList = fileHelper.readData(this)

//        arrayAdapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            android.R.id.text1,
//            itemList as List<Any>
//        )
        arrayAdapter = CustomAdapter(this, itemList)
        binding.lvTask.adapter = arrayAdapter

        for (i in 0 until arrayAdapter.count){
            Log.d("ListView", "position ${arrayAdapter.getItem(i)}")
        }

        binding.btnAdd.setOnClickListener {
            var itemNameTask = binding.edtInput.text.toString()
//            var itemStatusTask = binding.edtInput.text.toString()
            var itemTask = DataTask(itemNameTask, true)
            itemList.add(itemTask)
            binding.edtInput.setText("")

            fileHelper.writeData(itemList, applicationContext)
            arrayAdapter.notifyDataSetChanged()
            Log.d("itemList", itemList.toString())
        }

        registerForContextMenu(binding.lvTask)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(1,1,1,"Delete")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.itemId == 1){
            val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val position = info.position
            Log.d("position", position.toString())
            itemList.removeAt(position)
            arrayAdapter.notifyDataSetChanged()
            fileHelper.writeData(itemList, applicationContext)
        }
        return super.onContextItemSelected(item)
    }
}