package com.foxy.dialogactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = getString(R.string.app_name)

        val textView = TextView(applicationContext)
        textView.text = getString(R.string.tv_text)
        textView.setPadding(20, 20, 20, 20)
        setContentView(textView)
    }
}
