package com.example.swipetoshowbutton_recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipetoshowbutton_recyclerview.adapter.MyAdapter
import com.example.swipetoshowbutton_recyclerview.databinding.ActivityMainBinding
import com.example.swipetoshowbutton_recyclerview.helper.MyButton
import com.example.swipetoshowbutton_recyclerview.helper.MySwipeHelper
import com.example.swipetoshowbutton_recyclerview.listener.MyButtonClickListener
import com.example.swipetoshowbutton_recyclerview.model.Item

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var recycler_test : RecyclerView
    lateinit var adapter: MyAdapter
    lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recycler_test = binding.recyclerTest
        recycler_test.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recycler_test.layoutManager = layoutManager

        // add swipe
        val swipe = object:  MySwipeHelper(this, recycler_test,200){
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                //add button
                buffer.add(
                    MyButton(
                    this@MainActivity,
                    "Delete",
                    30,
                    R.drawable.icon_delete,
                    Color.parseColor("#FF9502"),
                        object:MyButtonClickListener{
                            override fun onClick(pos: Int) {
                               Toast.makeText(this@MainActivity, "Delete ID $pos",Toast.LENGTH_SHORT).show()
                            }

                        }
                )
                )

                buffer.add(
                    MyButton(
                        this@MainActivity,
                        "Update",
                        30,
                        R.drawable.icon_edit,
                        Color.parseColor("#FF3C30"),
                        object:MyButtonClickListener{
                            override fun onClick(pos: Int) {
                                Toast.makeText(this@MainActivity, "Update ID $pos",Toast.LENGTH_SHORT).show()
                            }

                        }
                    )
                )
            }

        }


        generateItem()

    }

    private fun generateItem() {
        val itemList = ArrayList<Item>()
        var i = 0;
        while( i < 20){
            itemList.add(
                Item("Pie 0${++i}", "100$", "https://pixabay.com/get/gbcc86bd36e0be27d486ca82c2a6a902451d65d9c3360b894746b6acb5add150e752553737dcb79d65893d3f15fe4150c_1280.jpg")
            )
            i++
        }
        adapter = MyAdapter(itemList, this)
        recycler_test.adapter = adapter
    }
}