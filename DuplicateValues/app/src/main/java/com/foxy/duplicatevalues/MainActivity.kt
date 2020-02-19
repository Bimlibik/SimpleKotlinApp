package com.foxy.duplicatevalues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_clear.setOnClickListener(listener)
        btn_search.setOnClickListener(listener)
    }

    private val listener = View.OnClickListener {
        when(it.id) {
            R.id.btn_search -> search()
            R.id.btn_clear -> clearFields()
        }
    }

    private fun search() {
        val array = ArrayList<String>(0)

        if (field_first_value.text.isNotEmpty()) {
            array.add(field_first_value.text.toString())
        }
        if (field_second_value.text.isNotEmpty()) {
            array.add(field_second_value.text.toString())
        }

        val result = findIntersection(array)
        if (result.isNotEmpty()) {
            tv_result.text = result.toString()
        } else {
            tv_result.text = "No duplicates found or data entered is incorrect"
        }
    }

    private fun clearFields() {
        field_first_value.text.clear()
        field_second_value.text.clear()
        tv_result.text = ""
    }


    // iterates over each number in both arrays
    private fun findDuplicate(array: ArrayList<String>) : ArrayList<String> {
        val duplicate = ArrayList<String>(0)

        if (array.isEmpty()) {
            return duplicate
        }

        val firstString = array[0].split(", ")
        val secondString = array[1].split(", ")

        for (num1 in firstString) {
            var count1: Int
            try {
                count1 = num1.toInt()
            } catch (ex: NumberFormatException) {
                Toast.makeText(applicationContext, "Invalid data format", Toast.LENGTH_LONG).show()
                duplicate.clear()
                break
            }

            for (num2 in secondString) {
                var count2: Int
                try {
                    count2 = num2.toInt()
                } catch (ex: NumberFormatException) {
                    Toast.makeText(applicationContext, "Invalid data format", Toast.LENGTH_LONG).show()
                    duplicate.clear()
                    break
                }
                if (count1 == count2) {
                    duplicate.add("$count2")
                }
            }
        }
        return duplicate
    }

    // works if arrays are sorted ascending
    private fun findIntersection(array: ArrayList<String>) : ArrayList<String> {
        val duplicate = ArrayList<String>(0)

        if (array.isEmpty()) {
            return duplicate
        }

        val firstString = array[0].split(", ")
        val secondString = array[1].split(", ")
        var indexFirst = 0
        var indexSecond = 0

        while (indexFirst < firstString.size && indexSecond < secondString.size) {
            val num1 = firstString[indexFirst]
            val num2 = secondString[indexSecond]

            when {
                num1 == num2 -> {
                    duplicate.add(num1)
                    indexFirst++
                    indexSecond++
                }
                num1 > num2 -> {
                    indexSecond++
                }
                num1 < num2 -> {
                    indexFirst++
                }
            }
        }
        return duplicate
    }
}
