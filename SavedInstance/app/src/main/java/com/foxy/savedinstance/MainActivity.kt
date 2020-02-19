package com.foxy.savedinstance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var msg: String = "First run"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            msg = savedInstanceState.getString("Key", "Default")
            tv.text = "Hello again!"
        }
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        msg = "Restored from memory"
        outState.putString("Key", msg)
    }


}
