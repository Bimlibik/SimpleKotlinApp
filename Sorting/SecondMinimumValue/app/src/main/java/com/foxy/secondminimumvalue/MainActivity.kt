package com.foxy.secondminimumvalue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener { search() }
        btn_clear.setOnClickListener { clear() }
    }

    private fun search() {
        val array = ArrayList<String>(0)
        if (field.text.isNotEmpty()) {
            array.add(field.text.toString())
        }

        val result = findSecondMinimalValue(array)
        if (result.isNotEmpty()) {
            tv_result.text = result
        } else {
            tv_result.text = "Not found"
        }
    }

    private fun clear() {
        field.text.clear()
        tv_result.text = ""
    }

    private fun findSecondMinimalValue(array: ArrayList<String>) : String {
        if (array.isEmpty()) {
            return ""
        }

        val splitArray = array[0].split(", ")
        var min1 = splitArray[0].toInt()
        var min2 = splitArray[1].toInt()

        for (i in splitArray) {
            if (i.toInt() == min1) {
                continue
            }

            if (i.toInt() < min1) {
                min2 = min1
                min1 = i.toInt()
                continue
            }

            if (i.toInt() < min2) {
                min2 = i.toInt()
            }
        }

        return min2.toString()
    }
}
