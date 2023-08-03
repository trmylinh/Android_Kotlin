package com.example.broadcastreceiver_simple

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.broadcastreceiver_simple.databinding.ActivityMainBinding
import java.util.zip.Inflater

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val broadcast = BroadcastAirPlane()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //khai bao su dung Broadcast
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        this.registerReceiver(broadcast,filter)

    }
}