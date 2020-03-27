package com.foxy.basicsorting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val size = 10
    private val bound = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_clear.setOnClickListener { clear() }
        btn_generate.setOnClickListener { generateValue() }

        btn_selection_sort.setOnClickListener { selectionSort(getArray()) }
        btn_bubble_sort.setOnClickListener { bubbleSort(getArray()) }
        btn_insertion_sort.setOnClickListener { insertionSort(getArray()) }
        btn_merge_sort.setOnClickListener { mergeSort(getArray()) }
        btn_quick_sort.setOnClickListener { quickSort(getArray()) }
    }

    private fun clear() {
        field.text.clear()
        tv_result.text = ""
    }

    private fun generateValue() {
        val random = Random()
        var values = ""

        for (i in 0 until size) {
            values += if (i == size - 1) {
                "${random.nextInt(bound)}"
            } else {
                "${random.nextInt(bound)}, "
            }
        }

        field.setText(values)
    }

    private fun selectionSort(array: MutableList<String>) {
        if (array.isEmpty()) return

        for (i in array.indices) {
            var min = i
            for (j in i + 1 until array.size) {
                if (array[j].toInt() < array[min].toInt()) {
                    min = j
                }
            }
            if (i != min) {
                val value = array[i]
                array[i] = array[min]
                array[min] = value
            }
        }

        tv_result.text = array.toString()
    }

    private fun bubbleSort(array: MutableList<String>) {
        if (array.isEmpty()) return

        var swap = true

        while (swap) {
            swap = false
            for (i in 1 until array.size) {
                if (array[i].toInt() < array[i - 1].toInt()) {
                    val value = array[i - 1]
                    array[i - 1] = array[i]
                    array[i] = value
                    swap = true
                }
            }
        }

        tv_result.text = array.toString()
    }

    private fun insertionSort(array: MutableList<String>) {
        if (array.isEmpty()) return

        for (i in 1 until array.count()){
            val value = array[i]
            var count = i
            while (count > 0 && value.toInt() < array[count - 1].toInt()){
                array[count] = array[count - 1]
                count -= 1
            }
            array[count] = value
        }
        tv_result.text = array.toString()
    }

    //===============================MERGE SORT========================================

    private fun mergeSort(array: MutableList<String>) {
        if (array.isEmpty()) return

        val sortedList = getMergeArray(array)
        tv_result.text = sortedList.toString()
    }

    private fun getMergeArray(array: MutableList<String>) : MutableList<String> {
        if (array.size <= 1) {
            return array
        }
        val mid = array.size / 2
        val leftArray = array.subList(0, mid)
        val rightArray = array.subList(mid, array.size)
        return merge(getMergeArray(leftArray), getMergeArray(rightArray))
    }

    private fun merge(left: List<String>, right: List<String>) : MutableList<String> {
        var indexLeft = 0
        var indexRight = 0
        val newArray = mutableListOf<String>()

        while (indexLeft < left.count() && indexRight < right.count()) {
            if (left[indexLeft].toInt() <= right[indexRight].toInt()) {
                newArray.add(left[indexLeft])
                indexLeft++
            } else {
                newArray.add(right[indexRight])
                indexRight++
            }
        }

        while (indexLeft < left.size) {
            newArray.add(left[indexLeft])
            indexLeft++
        }

        while (indexRight < right.size) {
            newArray.add(right[indexRight])
            indexRight++
        }
        return newArray
    }

    //================================QUICK SORT======================================

    private fun quickSort(array: MutableList<String>) {
        if (array.isEmpty()) return

        val sortedList = getQuickArray(array)
        tv_result.text = sortedList.toString()
    }

    private fun getQuickArray(array: MutableList<String>) : MutableList<String> {
        if (array.size < 2) {
            return array
        }

        val pivot = array[array.count() / 2].toInt()
        val equal = array.filter { it.toInt() == pivot } as MutableList<String>
        val less = array.filter { it.toInt() < pivot } as MutableList<String>
        val greater = array.filter { it.toInt() > pivot } as MutableList<String>
        return (getQuickArray(less) + equal + getQuickArray(greater)) as MutableList<String>
    }

    private fun getArray() : MutableList<String> {
        val array = ArrayList<String>(0)

        if (field.text.isEmpty()) {
            tv_result.text = "Field is empty"
            return array
        }

        array.add(field.text.toString())
        return array[0].split(", ").toMutableList()
    }
}
