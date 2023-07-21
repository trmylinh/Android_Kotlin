package com.example.listview_nangcao

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.listview_nangcao.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // lateinit là khởi tạo muộn
    // thông thường khai báo biến kotlin sẽ yêu cầu khởi tạo (gán giá trị cho biến)
    // ta có thể dùng lateinit để gán giá trị sau
    lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // khai bao danh sach cac item list
        var list = mutableListOf<DataFilm>()
        list.add(DataFilm(R.drawable.food10, "Food 10", "Delicious"))
        list.add(DataFilm(R.drawable.food5, "Food 5", "Delicious"))
        list.add(DataFilm(R.drawable.food6, "Food 6", "Delicious"))
        list.add(DataFilm(R.drawable.food7, "Food 7", "Delicious"))
        list.add(DataFilm(R.drawable.food9, "Food 9", "Delicious"))

        customAdapter = CustomAdapter(this, list)

        binding.lvFilm.adapter = customAdapter

        binding.lvFilm.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            //AdapterView: AdapterView nơi nhấp chuột xảy ra
            // view view: chế độ xem trong chê độ xem bộ điều hợp đã được nhấp
            // (đây sẽ là chế độ xem do bộ điều hợp cung cấp)
            // i int: vị trí của khung hình trong bộ điều hợp
            // l long: Id hàng của mục đã được nhấp
            Toast.makeText(this, "You choose ${list[i].title}", Toast.LENGTH_SHORT).show()
        }

    }
}