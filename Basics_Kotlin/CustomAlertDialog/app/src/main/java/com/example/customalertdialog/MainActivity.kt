package com.example.customalertdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.customalertdialog.databinding.ActivityMainBinding
import com.example.customalertdialog.databinding.CustomDialogBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var dialog : AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            showDialogNormal()
//            showDialogBinding()
        }
    }

    // su dung viewBinding
    private fun showDialogBinding() {
        val build = AlertDialog.Builder(this, R.style.ThemeCustom)
        val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(this))
        build.setView(dialogBinding.root)

        //button close
        dialogBinding.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // button join
        dialogBinding.btnJoin.setOnClickListener {
            Toast.makeText(this, "You choose join", Toast.LENGTH_LONG).show()
        }
        dialog = build.create()
        dialog.show()
    }

    private fun showDialogNormal() {
        val build = AlertDialog.Builder(this, R.style.ThemeCustom)
        val view = layoutInflater.inflate(R.layout.custom_dialog, null)
        build.setView(view)

        // button close
        val btnClose = view.findViewById<ImageButton>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        // button join
        val btnJoin = view.findViewById<Button>(R.id.btnJoin)
        btnJoin.setOnClickListener{
            Toast.makeText(this, "You choose join", Toast.LENGTH_LONG).show()
        }

        dialog = build.create()
        dialog.show()
    }
}