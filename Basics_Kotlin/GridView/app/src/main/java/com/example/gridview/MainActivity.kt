package com.example.gridview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.gridview.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val list = mutableListOf<DataFilm>()
        list.add(DataFilm(R.drawable.food1, "Food1"))
        list.add(DataFilm(R.drawable.food2, "Food2"))
        list.add(DataFilm(R.drawable.food3, "Food3"))
        list.add(DataFilm(R.drawable.food4, "Food4"))
        list.add(DataFilm(R.drawable.food5, "Food5"))
        list.add(DataFilm(R.drawable.food6, "Food6"))
        list.add(DataFilm(R.drawable.food7, "Food7"))
        list.add(DataFilm(R.drawable.food8, "Food8"))
        list.add(DataFilm(R.drawable.food9, "Food9"))


        val customGridView = CustomGridView(this, list)
        binding.gvFilm.adapter = customGridView

        binding.gvFilm.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "You choose ${list[i].name}", Toast.LENGTH_SHORT).show()
        }
    }
}