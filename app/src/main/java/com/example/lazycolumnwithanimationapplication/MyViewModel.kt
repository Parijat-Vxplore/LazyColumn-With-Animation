package com.example.lazycolumnwithanimationapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _items = mutableListOf<String>()
    val items: List<String> get() = _items

    private var itemCount = 0

    fun addItem() {
        viewModelScope.launch {
            _items.add("Item #${itemCount++}")
        }
    }
    fun addAt(position: Int) {
        viewModelScope.launch {
            _items.add(position,generateRandomString(10))
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
            _items.removeAt(index)
        }

    }


}