package com.example.viewevent_tinhtong

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.viewevent_tinhtong.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // khoi tao viewbinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // dung kotlin-android-extensions thì không cần khai bao như này
        // dùng luôn tên biến đã đặt bên design
        // nhưng android studio lại không hỗ trợ nữa
        // nên là đổi sang dùng viewbinding

        //val edtEnterA = findViewById<EditText>(R.id.edtEnterA)
        val edtEnterB = findViewById<EditText>(R.id.edtEnterB)
        //val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnLongClick = findViewById<Button>(R.id.btnLongClick)

        // setText cho editText
//        edtEnterA.setText("Enter A:");

        //event
        btnAdd.setOnClickListener {
            val a = binding.edtEnterA.text.toString().toInt()
            val b = edtEnterB.text.toString().toInt()

            // đối với textView nên dùng .text thay cho setText()
            binding.txtResult.text = ("Result: " + (a+b).toString())
        }

        // nếu dùng hàm set... như này thì chỉ thiết lập 1 sự kiện duy nhất cho 1 view duy nhất
        btnReset.setOnClickListener{
            binding.edtEnterA.setText("")
            edtEnterB.setText("")
            binding.txtResult.text = "Result"

            //Toast
            Toast.makeText(this, "Input is empty", Toast.LENGTH_SHORT).show()
        }

        //tạo sự kiện chia sẻ: 1 biến có thể chia sẻ sự kiện cho >= 2 view
        var eventShare : View.OnClickListener? = null
        eventShare = View.OnClickListener {
            if(it == btnMultiply) {
                val a = binding.edtEnterA.text.toString().toInt()
                val b = edtEnterB.text.toString().toInt()
                binding.txtResult.text = "Result: " + (a*b).toString()
            }
            else if (it == btnDivide){
                val a = binding.edtEnterA.text.toString().toDouble()
                val b = edtEnterB.text.toString().toDouble()
                binding.txtResult.text = "Result: " + (a/b).toString()
            }
        }
        btnMultiply.setOnClickListener(eventShare)
        btnDivide.setOnClickListener(eventShare)

        //dùng như này có thể chia sẻ sự kiện
        var eventLongClick: View.OnLongClickListener? = null
        eventLongClick = View.OnLongClickListener {
            if (it == btnLongClick) {
                Toast.makeText(this, "Long Click will be disappear", Toast.LENGTH_LONG).show()
                btnLongClick.visibility = View.GONE // an button
            }
            // trả về true rằng sự kiện đã được xử lý
            true
        }
        btnLongClick.setOnLongClickListener(eventLongClick)
    }
}