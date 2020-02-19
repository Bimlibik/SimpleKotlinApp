package com.foxy.bootreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    companion object {
        private const val BOOT_ACTION = "android.permission.RECEIVE_BOOT_COMPLETED"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(BOOT_ACTION)) {
            Toast.makeText(context, "Hello world!", Toast.LENGTH_LONG).show()
        }
    }
}
