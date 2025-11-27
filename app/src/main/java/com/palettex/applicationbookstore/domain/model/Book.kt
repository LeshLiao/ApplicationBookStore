package com.palettex.applicationbookstore.domain.model

data class BookStore(
    val bookStore: List<Book>
)

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val price: Int,
    val image: String,
    val details: String,
    var isLike: Boolean = false,
)
