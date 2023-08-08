package com.example.swipe_items_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipe_items_recyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    private lateinit var itemRv: RecyclerView
    private lateinit var itemList: ArrayList<String>
    private lateinit var itemAdapter: ItemRecyclerAdapter
    private lateinit var archiveRv: RecyclerView
    private lateinit var archiveList: ArrayList<String>
    private lateinit var archiveAdapter: ItemRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemRv = binding.itemRv
        archiveRv = binding.archiveRv

        itemList = ArrayList()
        archiveList = ArrayList()

        for (i in 1..10){
            itemList.add("Item-${i}")
        }
        itemAdapter = ItemRecyclerAdapter(itemList,this)
        val itemLayoutManager = LinearLayoutManager(this)

        itemRv.layoutManager = itemLayoutManager
        itemRv.adapter = itemAdapter

        archiveAdapter = ItemRecyclerAdapter(archiveList, this)
        val archiveLayoutManager = LinearLayoutManager(this)

        archiveRv.layoutManager = archiveLayoutManager
        archiveRv.adapter = archiveAdapter

        swipeToGesture(itemRv)

    }

    private fun swipeToGesture(itemRv: RecyclerView?){
        val swipeGesture = object: SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                var actionBtnTapped = false

                try{
                    when(direction){
                        ItemTouchHelper.LEFT -> {
                            val deleteItem = itemList[position]
                            itemList.removeAt(position)
                            itemAdapter.notifyItemRemoved(position)

                            val snackbar = Snackbar.make(this@MainActivity.itemRv, "Item Deleted", Snackbar.LENGTH_LONG)
                                .addCallback(object: BaseTransientBottomBar.BaseCallback<Snackbar>(){
                                    override fun onDismissed(
                                        transientBottomBar: Snackbar?,
                                        event: Int
                                    ) {
                                        super.onDismissed(transientBottomBar, event)
                                    }

                                    override fun onShown(transientBottomBar: Snackbar?) {
                                        transientBottomBar?.setAction("Undo"){
                                            itemList.add(position, deleteItem)
                                            itemAdapter.notifyItemInserted(position)
                                            actionBtnTapped = true
                                        }

                                        super.onShown(transientBottomBar)
                                    }
                                }).apply {
                                    animationMode = Snackbar.ANIMATION_MODE_FADE
                                }
                            snackbar.setActionTextColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.orangeRed
                                )
                            )
                            snackbar.show()
                        }
                        ItemTouchHelper.RIGHT -> {
                            val archiveItem = itemList[position]
                            itemList.removeAt(position)
                            itemAdapter.notifyItemRemoved((position))

                            archiveList.add(archiveItem)
                            archiveAdapter.notifyItemInserted(position)


                            val snackbar = Snackbar.make(this@MainActivity.itemRv, "Item Archived", Snackbar.LENGTH_LONG)
                                .addCallback(object: BaseTransientBottomBar.BaseCallback<Snackbar>(){
                                    override fun onDismissed(
                                        transientBottomBar: Snackbar?,
                                        event: Int
                                    ) {
                                        super.onDismissed(transientBottomBar, event)
                                    }

                                    override fun onShown(transientBottomBar: Snackbar?) {
                                        transientBottomBar?.setAction("Undo"){
                                            itemList.add(position, archiveItem)
                                            itemAdapter.notifyItemInserted(position)
                                            archiveList.removeAt(archiveList.size-1)
                                            archiveAdapter.notifyItemRemoved(archiveList.size)
                                            actionBtnTapped = true
                                        }

                                        super.onShown(transientBottomBar)
                                    }
                                }).apply {
                                    animationMode = Snackbar.ANIMATION_MODE_FADE
                                }
                            snackbar.setActionTextColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.orangeRed
                                )
                            )
                            snackbar.show()
                        }
                    }
                }
                catch (e: Exception){
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)

        touchHelper.attachToRecyclerView(itemRv)
    }

}