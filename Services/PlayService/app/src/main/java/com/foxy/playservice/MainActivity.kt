package com.foxy.playservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener { startService() }
        btn_stop.setOnClickListener { stopService() }
    }

    private fun startService() {
        startService(Intent(this, PlayService::class.java))
    }

    private fun stopService() {
        stopService(Intent(this, PlayService::class.java))
    }
}
