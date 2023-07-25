package com.example.intent

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.intent.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Explicit Intents
        // goi man hinh 2 tu man hinh MainActivity
        binding.btnGo.setOnClickListener {
            val intent1 = Intent(this, Screen2::class.java)

            //truyen du lieu truc tiep qua Intent
            intent1.putExtra("bienString", "My Linh")
            intent1.putExtra("bienDouble", 1.65)
            intent1.putExtra("bienBoolean", true)
            startActivity(intent1)
        }

        //  Implicit Intents
        // goi URL tu man hinh MainActivity
        binding.btnGo3.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_VIEW, Uri.parse(("https://www.youtube.com/")))
            startActivity(intent1)
        }



    }
}
