package com.example.sqlite_simple

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sqlite_simple.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var helper = MyDBHelper(applicationContext)

        // doc database
        val db = helper.readableDatabase

        // thuc hien 1 lenh -> roi tra ve kqua (lenh tim kiem truy van)
        val rs = db.rawQuery("select * from users", null)

        if(rs.moveToFirst()){
            Toast.makeText(applicationContext, rs.getString(1), Toast.LENGTH_LONG).show()
            Log.d("sql", rs.getString(1))
        }


        binding.btnAdd.setOnClickListener {
            val contentValue = ContentValues()
            contentValue.put("USERNAME", binding.edtUsername.text.toString())
            contentValue.put("PWD", binding.edtPassword.text.toString())

            db.insert("USERS", null, contentValue)

            binding.edtUsername.setText("")
            binding.edtPassword.setText("")

            // con tro se nhap nhay o input username
            binding.edtUsername.requestFocus()
        }
    }
}