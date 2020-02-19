package com.foxy.powerdisconnected

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            Toast.makeText(context, "Message found: ${intent.action}", Toast.LENGTH_LONG).show()
        }
    }
}
