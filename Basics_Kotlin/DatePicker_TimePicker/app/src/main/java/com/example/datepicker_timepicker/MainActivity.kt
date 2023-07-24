package com.example.datepicker_timepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.datepicker_timepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    //ngay thang nam hien tai
    val today = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //set lai thoi gian khi click btnTime
        // lang nghe event
        binding.btnTime.setOnClickListener {
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                // i: giờ khi người dùng chọn
                // i2: phút
                binding.txtTime.text = "${i}:${i2}"
            }, today.get(Calendar.HOUR_OF_DAY),today.get(Calendar.MINUTE), true).show()
        }

        // btnDate
        binding.btnDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                // i : nam
                // i2 : thang
                //i3 : ngay
                today.set(i, i2,i3)
                val dateFormat = SimpleDateFormat("EEE, dd.MM.yyyy")
                binding.txtDate.text = dateFormat.format(today.time)
            },today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE)).show()
        }
    }
}