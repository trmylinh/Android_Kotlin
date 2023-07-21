package com.example.imagebutton_imageview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.imagebutton_imageview.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addEvents()
    }

    private fun addEvents() {
//        binding.radPic1.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked){
//                binding.image.setImageResource(R.drawable.food1)
//            }
//        }
//        binding.radPic2.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked){
//                binding.image.setImageResource(R.drawable.food3)
//            }
//        }

        // cach khac
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radPic1->  binding.image.setImageResource(R.drawable.food1)
                R.id.radPic2 ->  binding.image.setImageResource(R.drawable.food3)
            }
        }

        // thoat
        binding.imageButton.setOnClickListener {
            finish()
        }
    }


}