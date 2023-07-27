package com.example.alertdialog

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.alertdialog.databinding.ActivityMainBinding
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.apply {
                // tieu de cua thong bao
                setTitle("Confirm to exit?!")

                // ndung thong bao
                setMessage("Do you want to close this app?")

                // nut phu dinh "NO" or "Cancle"
                setNegativeButton("Cancle"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }

                // nut khang dinh
                setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                    finish()
                }

                // ngan khong cho dong dialog khi click ra ngoai
                setCancelable(false)
            }

            dialog.show()
        }

    }
}