package com.example.oop_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sv1 : Student = Student("Linh", "Ha Noi", 2002)

        Log.d("sinh vien", sv1.getName() + "-" + sv1.getYear());
    }
}