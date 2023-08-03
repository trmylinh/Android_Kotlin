package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharedpreferences.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    var count = 0
    var send: String? = null
    var message : String? = null
    var isChecked : Boolean? = null

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            count++
            binding.btnCount.text = "Count: ${count.toString()}"
        }

    }

    // luu trang thai -> onPause()
    override fun onPause() {
        saveData()
        super.onPause()
    }

    // khoi phuc du lieu -> onResume()
    override fun onResume() {
        restoreData()
        super.onResume()
    }

    private fun restoreData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
        send = sharedPreferences.getString("keySend", null)
        message = sharedPreferences.getString("keyMessage", null)
        isChecked = sharedPreferences.getBoolean("keyIsChecked", false)
        count = sharedPreferences.getInt("keyCount", 0)

        binding.edtSend.setText(send)
        binding.edtMessage.setText(message)
        binding.btnCount.text= "Count: ${count.toString()}"
        binding.checkBox.isChecked = isChecked!!
    }

    private fun saveData() {
        //Context.MODE_PRIVATE: cấp quyền truy cập nội tại trong ứng dụng
        // tức là mọi nơi trong ứng dụng đều có thể truy cập được, bên ngoài ứng dụng thì không truy cập được
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        send = binding.edtSend.text.toString()
        message = binding.edtMessage.text.toString()
        isChecked = binding.checkBox.isChecked

        //luu thong tin
        // key - phai la duy nhat
        val editor = sharedPreferences.edit()
        editor.putString("keySend", send)
        editor.putString("keyMessage", message)
        editor.putBoolean("keyIsChecked", isChecked!!)
        editor.putInt("keyCount", count)

        editor.apply()

        //thong bao data da duoc luu
        Toast.makeText(applicationContext, "Data Saved!", Toast.LENGTH_SHORT).show()
    }
}