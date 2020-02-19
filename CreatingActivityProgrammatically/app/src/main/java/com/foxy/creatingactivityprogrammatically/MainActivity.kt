package com.foxy.creatingactivityprogrammatically

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(applicationContext)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        layout.id = R.id.layout_id
        layout.layoutParams = layoutParams
        layout.orientation = VERTICAL

        val imageView = ImageView(applicationContext)
        val imageViewParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        imageView.id = R.id.image_view_id
        imageView.setImageResource(R.mipmap.ic_launcher)
        imageViewParams.gravity = Gravity.CENTER
        imageView.layoutParams = imageViewParams

        layout.addView(imageView)
        setContentView(layout)

        layout.setOnClickListener(listener)
        imageView.setOnClickListener(listener)
    }

    private var listener = View.OnClickListener {
        val id = it.id
        Toast.makeText(applicationContext, "ID: $id clicked", Toast.LENGTH_LONG).show()
    }
}
