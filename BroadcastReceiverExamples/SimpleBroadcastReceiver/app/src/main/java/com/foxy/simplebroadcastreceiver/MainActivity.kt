package com.foxy.simplebroadcastreceiver

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NEW_MSG = "com.foxy.NEW_MSG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_send.setOnClickListener(listener)
    }

    private fun sendMessage() {
        Intent().also { intent ->
            intent.action = NEW_MSG
            intent.putExtra("msg","Hi! I'm message.")
            sendBroadcast(intent)
        }
    }

    private val listener = View.OnClickListener{
        when(it.id) {
            R.id.btn_send -> sendMessage()
        }
    }
}
