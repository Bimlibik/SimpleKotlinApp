package com.foxy.batterystats

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.LevelListDrawable
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryReceiver)
    }

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                if (intent.action.equals(Intent.ACTION_BATTERY_CHANGED, true)) {
                    tv_level.text = getString(
                        R.string.battery_level,
                        intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0))
                    tv_voltage.text = getString(
                        R.string.voltage,
                        intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) / 1000)
                    tv_temperature.text = getString(
                        R.string.temperature,
                        intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10)
                    tv_technology.text = getString(
                        R.string.technology,
                        intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY))
                    tv_status.text = getString(R.string.status, getStatus(intent))
                    tv_charge.text = getString(R.string.charge, getCharge(intent))
                    tv_health.text = getString(R.string.health, getHealth(intent))

                    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    val scale = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                    tv_current.text = getString(R.string.current, level / scale)

                    val iconId = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0)
                    val icon: LevelListDrawable = resources.getDrawable(iconId) as LevelListDrawable
                    img_battery_icon.background = icon
                }
            }

        }
    }

    private fun getStatus(intent: Intent): String {
        val status = intent.getIntExtra(
            BatteryManager.EXTRA_STATUS,
            BatteryManager.BATTERY_STATUS_UNKNOWN)
        return when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not charging"
            BatteryManager.BATTERY_STATUS_FULL -> "Full charge"
            else -> "Unknown"
        }
    }

    private fun getCharge(intent: Intent): String {
        val chargePlug = intent.getIntExtra(
            BatteryManager.EXTRA_PLUGGED,
            BatteryManager.BATTERY_STATUS_UNKNOWN)
        return when(chargePlug) {
            BatteryManager.BATTERY_PLUGGED_USB -> "Charged by USB"
            BatteryManager.BATTERY_PLUGGED_AC -> "Charged by mains"
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Wireless charger"
            else -> "Unknown"
        }
    }

    private fun getHealth(intent: Intent): String {
        val health = intent.getIntExtra(
            BatteryManager.EXTRA_HEALTH,
            BatteryManager.BATTERY_HEALTH_UNKNOWN)
        return when (health) {
            BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
            BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over voltage"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
            else -> "Unknown"
        }
    }
}
