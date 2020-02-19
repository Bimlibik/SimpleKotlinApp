package com.foxy.batterylow

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            Intent.ACTION_BATTERY_LOW -> Toast.makeText(context, "Battery is low and hungry", Toast.LENGTH_LONG).show()
            Intent.ACTION_POWER_CONNECTED -> Toast.makeText(context, "Battery is charging", Toast.LENGTH_LONG).show()
            Intent.ACTION_POWER_DISCONNECTED -> Toast.makeText(context, "Battery is't charging", Toast.LENGTH_LONG).show()
        }
    }
}
