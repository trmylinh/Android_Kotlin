package com.example.swipetoshowbutton_recyclerview.helper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.LinkedList

abstract class MySwipeHelper(
    private val context: Context,
    private val recyclerView: RecyclerView,
    private val buttonWidth: Int
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

    var buttonList: MutableList<MyButton>?=null
    private lateinit var gestureDetector: GestureDetector
    private var swipePostion = -1
    private var swipeThreshold = 0.5f
    private var buttonBuffer : MutableMap<Int, MutableList<MyButton>>
    private lateinit var removeQueue : LinkedList<Int>

    abstract fun instantiateMyButton(viewHolder: RecyclerView.ViewHolder, buffer: MutableList<MyButton>)

    private val gestureListener = object: GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            for (button in buttonList!!){
                if(button.onClick(e.x,e.y)){
                    break
                }
            }
            return true
        }
    }

    private val onTouchListener = View.OnTouchListener { view, motionEvent ->
        if(swipePostion < 0) return@OnTouchListener false
        val point = Point(motionEvent.rawX.toInt(), motionEvent.rawX.toInt())
        val swipeViewHolder = recyclerView.findViewHolderForAdapterPosition(swipePostion)
        val swipeItem = swipeViewHolder!!.itemView
        val rect = Rect()
        swipeItem.getGlobalVisibleRect(rect)

        if(motionEvent.action == MotionEvent.ACTION_DOWN ||
            motionEvent.action == MotionEvent.ACTION_MOVE ||
            motionEvent.action == MotionEvent.ACTION_UP){
            if(rect.top < point.y && rect.bottom > point.y){
                gestureDetector.onTouchEvent((motionEvent))
            }
            else{
                removeQueue.add(swipePostion)
                swipePostion = -1
                recoverSwipe()
            }

        }
        if (motionEvent.action == MotionEvent.ACTION_UP && view.isClickable) {
            view.performClick()
        }
        false
    }

    @Synchronized
    private fun recoverSwipe(){
        while(!removeQueue.isEmpty()){
            val pos = removeQueue.poll()?.toInt()
            if(pos != null){
                if(pos > -1){
                    recyclerView.adapter!!.notifyItemChanged(pos)
                }
            }

        }
    }

    init{
        buttonList = ArrayList()
        gestureDetector = GestureDetector(context, gestureListener)
        recyclerView.setOnTouchListener(onTouchListener)
        buttonBuffer = HashMap()
        removeQueue = IntLinkedList()

        attachSwipe()
    }

    private fun attachSwipe() {
        val itemTouchHelper = ItemTouchHelper(this)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    class IntLinkedList : LinkedList<Int>(){
        override fun contains(element: Int): Boolean {
            return false
        }

        override fun lastIndexOf(element: Int): Int {
            return element
        }

        override fun remove(element: Int): Boolean {
            return false
        }

        override fun indexOf(element: Int): Int {
            return element
        }

        override fun add(element: Int): Boolean {
            return if(contains(element)){
                false
            } else {
                super.add(element)
            }
        }


    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val pos = viewHolder.adapterPosition
        if(swipePostion != pos){
            removeQueue.add(swipePostion)
            swipePostion = pos
            if(buttonBuffer.containsKey(swipePostion)){
                buttonList = buttonBuffer[swipePostion]
            }
            else{
                buttonList!!.clear()
            }
            buttonBuffer.clear()
            swipeThreshold = 0.5f*buttonList!!.size.toFloat()* buttonWidth.toFloat()
            recoverSwipe()
        }
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return swipeThreshold
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return 0.1f*defaultValue
    }

    override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
        return 0.5f*defaultValue
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val pos = viewHolder.adapterPosition
        var translationX: Float = dX
        val itemView = viewHolder.itemView
        if(pos < 0){
            swipePostion = pos
            return
        }
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if(dX < 0){
                var buffer : MutableList<MyButton> = ArrayList()
                if(!buttonBuffer.containsKey(pos)){
                    instantiateMyButton(viewHolder, buffer)
                    buttonBuffer[pos] = buffer
                }
                else{
                    buffer = buttonBuffer[pos]!!
                }
                translationX = dX*buffer.size.toFloat() * buttonWidth.toFloat() / itemView.width
                drawButton(c, itemView,buffer,pos, translationX)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawButton(c: Canvas, itemView: View, buffer: MutableList<MyButton>, pos: Int, translationX: Float) {
        var right = itemView.right.toFloat()
        val dButtonWidth = -1 * translationX/buffer.size
        for(button in buffer){
            val left = right - dButtonWidth
            button.onDraw(c, RectF(left, itemView.top.toFloat(), right, itemView.bottom.toFloat()),pos)
            right = left
        }
    }

}


