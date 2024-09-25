package com.example.lazycolumnwithanimationapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MyViewModel : ViewModel() {
    val items = mutableStateListOf<String>()

    private var itemCount = 0

    fun addItem() {
        viewModelScope.launch {
            items.add("Item #${itemCount++}")
        }
    }
    fun addAt(position: Int) {
        viewModelScope.launch {
            items.add(position,generateRandomString(10))
        }
    }
    fun getItemCount():Int{
        return items.size
    }

    fun generateRandomString(length: Int): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { characters.random() }
            .joinToString("")
    }

    fun removeAt(index: Int) {
        viewModelScope.launch {
            items.removeAt(index)
        }

    }

    fun reorder(maxTry: Int = 8) {
        if(maxTry < 1){
            return
        }
        if(getItemCount() < 2){
            return
        }
        val firstIndex = Random.nextInt(0, getItemCount())
        val secondIndex = Random.nextInt(0, getItemCount())
        if(firstIndex == secondIndex){
            return reorder(maxTry - 1)
        }
        val tempList = mutableListOf<String>()
        tempList.addAll(items)
        val temp = tempList[firstIndex]
        tempList[firstIndex] = tempList[secondIndex]
        tempList[secondIndex] = temp
        items.clear()
        items.addAll(tempList)
    }


}