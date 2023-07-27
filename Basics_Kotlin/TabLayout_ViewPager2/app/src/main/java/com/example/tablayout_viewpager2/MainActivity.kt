package com.example.tablayout_viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tablayout_viewpager2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = adapter


        // TabLayoutMediator là 1 lớp trợ giúp trong Android
        // kết nối giữa TabLayout và ViewPager
        // Khi dử dụng ViewPager để hiển thị các mục dữ liệu trong ứng dụng
        // bạn có thể sủ dụng TabLayoutMediator để hiển thị các tab tương ứng
        // với các trang của ViewPager
        TabLayoutMediator(binding.tabLayout, binding.pager){tab, position ->
            when(position){
                0 -> {tab.text = "Tab 1"}
                1 -> {tab.text = "Tab 2"}
                2 -> {tab.text = "Tab 3"}
                3 -> {tab.text = "Tab 4"}
                4 -> {tab.text = "Tab 5"}
            }
        }.attach()
    }
}