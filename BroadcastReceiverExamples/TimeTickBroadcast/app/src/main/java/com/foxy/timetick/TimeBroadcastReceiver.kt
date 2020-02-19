package com.foxy.timetick

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class TimeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val time = SimpleDateFormat("hh:mm:ss a").format(Date())
        Toast.makeText(context, "Current time: $time", Toast.LENGTH_LONG).show()
    }
}