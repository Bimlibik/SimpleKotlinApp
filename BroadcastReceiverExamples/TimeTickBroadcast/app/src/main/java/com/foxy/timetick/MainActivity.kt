package com.foxy.timetick

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val timeBroadcastReceiver = TimeBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_register.setOnClickListener(listener)
        btn_unregister.setOnClickListener(listener)
    }

    private fun registerTimeBroadcastReceiver() {
        registerReceiver(timeBroadcastReceiver, IntentFilter("android.intent.action.TIME_TICK"))
        Toast.makeText(applicationContext, "Receiver is on", Toast.LENGTH_LONG).show()
    }

    private fun unregisterTimeBroadcastReceiver() {
        unregisterReceiver(timeBroadcastReceiver)
        Toast.makeText(applicationContext, "Receiver off", Toast.LENGTH_LONG).show()
    }

    private val listener = View.OnClickListener {
        when(it.id) {
            R.id.btn_register -> registerTimeBroadcastReceiver()
            R.id.btn_unregister -> unregisterTimeBroadcastReceiver()
        }
    }
}
