package com.example.customspinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.customspinner.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //custom spinner
        setUpCustomSpinner()

    }

    private fun setUpCustomSpinner() {
        val listFood = mutableListOf<DataFood>()
        listFood.add(DataFood(R.drawable.cam, "Cam"))
        listFood.add(DataFood(R.drawable.duahau, "Dua Hau"))
        listFood.add(DataFood(R.drawable.tao, "Tao"))
        listFood.add(DataFood(R.drawable.sauchung, "Sau Rieng"))
        listFood.add(DataFood(R.drawable.xoai, "Xoai"))

        val customSpinner = CustomSpinnerClass(this, listFood)
        binding.spCustom.adapter = customSpinner
    }
}