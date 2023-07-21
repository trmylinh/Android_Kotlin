package com.example.checkbox_radiobutton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.checkbox_radiobutton.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        addEvents()
    }

    private fun handleChooseFavorite(){
        var s : String = ""
        if(binding.chkDance.isChecked){
            s += binding.chkDance.text.toString()+"\n"
        }
        if(binding.chkMovie.isChecked){
            s += binding.chkMovie.text.toString()+"\n"
        }
        if(binding.chkSing.isChecked){
            s += binding.chkSing.text.toString()+"\n"
        }
        if(binding.chkSporty.isChecked){
            s += binding.chkSporty.text.toString()+"\n"
        }
        if(binding.chkTravel.isChecked){
            s += binding.chkTravel.text.toString()+"\n"
        }
        // gan vao  textView Result
        if(s == ""){
            Toast.makeText(this, "Please choose everything", Toast.LENGTH_SHORT).show()
        } else {
            binding.txtResult.text = "List of your favorite:\n$s"
        }
    }

    private fun handleChooseSex() {
        var result : String = ""
        if(binding.rdMale.isChecked){
            result = binding.rdMale.text.toString()
        }
        if(binding.rdFemale.isChecked){
            result = binding.rdFemale.text.toString()
        }

        if(result == ""){
            Toast.makeText(this, "Please choose everything", Toast.LENGTH_SHORT).show()
        } else {
            binding.txtResult2.text = "Sex: $result"
        }
    }
    private fun addEvents(){
        // xu ly khi click button choose
        binding.btnChoose.setOnClickListener {
            handleChooseFavorite()
            handleChooseSex()
        }
    }
}