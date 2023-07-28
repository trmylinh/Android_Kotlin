package com.example.optionmenu_contextmenu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.optionmenu_contextmenu.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private var selectedOptions = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // dang ki menu ngu canh
        registerForContextMenu(binding.txtContext)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //groupId
        // itemId
        // order - thứ tự
        // title
        menu?.add(1,1,1,"Red")
        menu?.add(1,2,2,"Blue")
        menu?.add(1,3,3,"Yellow")

        //sub menu
        val subMenu = menu?.addSubMenu("Age")
        // khong duoc trung itemId
//        subMenu?.add(2,11,1, ">18")?.setChecked(true)
        subMenu?.add(2,11,1, ">18")
        subMenu?.add(2,12,2, ">30")

        // setGroupCheckable(groupId, boolean, boolean)
        // 1.groupId: id của group mà muốn có nút check
        // 2.boolean: true để cho phép có dấu kiểm, false là không cho phép. Mặc định là false
        // 3. Chọn true để chỉ được chọn 1 item trong group
        //    Chọn false tích được nhiều lựa chọn
        subMenu?.setGroupCheckable(2, true, false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            1 -> binding.txtOption.setTextColor(Color.RED)
            2 -> binding.txtOption.setTextColor(Color.BLUE)
            3 -> binding.txtOption.setTextColor(Color.YELLOW)

            // tuong tac voi sub menu
            11 -> {
                if (!item.isChecked()) {
                    item.setChecked(true)
                    binding.txtContext.text = "Age ${item.title}"
                }
                else {
                    item.setChecked(false)
                }
            }
            12 -> {
                if (!item.isChecked()) {
                    item.setChecked(true)
                    binding.txtContext.text = "Age ${item.title}"
                }
                else{
                    item.setChecked(false)
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.add(3, 13, 1, "Red")
        menu?.add(3, 14, 2, "Green")
        menu?.add(3, 15, 3, "LTGRAY")

        menu?.setHeaderTitle("Pick color")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    // xu ly event tren context menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            13 -> binding.txtContext.setTextColor(Color.RED)
            14-> binding.txtContext.setTextColor(Color.GREEN)
            15 -> binding.txtContext.setTextColor(Color.LTGRAY)
        }
        return super.onContextItemSelected(item)
    }
}