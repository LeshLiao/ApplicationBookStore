package com.palettex.applicationbookstore.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palettex.applicationbookstore.data.MockData
import com.palettex.applicationbookstore.domain.model.Book
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainPageViewModel() : ViewModel() {

    private val _storeBook = MutableStateFlow<List<Book>>(emptyList())
    val storeBook = _storeBook.asStateFlow()

    private val _searchKeyWord = MutableStateFlow<String>("")
    val searchKeyWord = _searchKeyWord.asStateFlow()

    init {
        _storeBook.value = MockData.sampleBooks
    }

    fun search(query: String) {
        if (query.isNotEmpty()) {
            _storeBook.value = MockData.sampleBooks.filter { book ->
                book.title.contains(query, ignoreCase = true)
            }
        } else {
            _storeBook.value = MockData.sampleBooks
        }
    }

    fun priceLessThan(price: Int) {
        if (price > 0) {
            _storeBook.value = MockData.sampleBooks.filter { book ->
                book.price < price
            }
        } else {
            _storeBook.value = MockData.sampleBooks
        }
    }

    fun toggleFavorite(id: String) {
        _storeBook.value = _storeBook.value.map { book ->
            if (book.id == id) {
                book.copy(isLike = !book.isLike)  // Use copy to create new instance
            } else {
                book
            }
        }
    }
}