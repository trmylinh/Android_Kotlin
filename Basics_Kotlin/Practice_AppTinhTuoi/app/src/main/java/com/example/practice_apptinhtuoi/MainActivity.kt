package com.example.practice_apptinhtuoi

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.practice_apptinhtuoi.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDate.setOnClickListener {
            selectDate()
        }

    }

    private fun selectDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val dp =
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                // i year
                //i2 month
                //i3 day
                binding.txtDateSelected.text = "${i3}/${i2 + 1}/${i}"

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val dateBirth = sdf.parse("${i3}/${i2 + 1}/${i}")

                //?.let{} là một thao tác an toàn (safe call)
                // được sử dụng để kiểm tra một đối tượng có null không trước khi thực hiện các hoạt động trên nó.
                dateBirth?.let {
                    val dateBirthInMinute = dateBirth.time / 60000 //milisecond -> minute

                    //tgian hien tai
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinute = currentDate.time / 60000

                        val diff = currentDateInMinute - dateBirthInMinute
                        binding.txtAgeInMinute.text = diff.toString()
                    }

                }


            }, year, month, day)

        //gioi han thoi gian trong tuong lai
        //86400000: so giay tuong duong voi 24h (1 ngay)
        //Ví dụ, nếu thời gian hiện tại là 10 giờ sáng, 3/8/2023,
        // kết quả của biểu thức System.currentTimeMillis() - 86400000
        // sẽ là thời điểm 10 giờ sáng, 2/8/2023 (cách đây 24 giờ).

        // dp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dp.datePicker.maxDate = System.currentTimeMillis()

        dp.show()


    }
}