package com.palettex.applicationbookstore.data

import com.palettex.applicationbookstore.domain.model.Book

object MockData {
    val sampleBooks = listOf(
        Book(
            id = "0",
            title = "BookA",
            author = "Tom",
            price = 10,
            image = "https://fastly.picsum.photos/id/572/200/300.jpg?hmac=Rt4zD8IxoA-nMVDrBQ72mgbTVRfQ6OwW3MhWy_3lpdk",
            details = "Details_AAA"
        ),
        Book(
            id = "1",
            title = "BookB",
            author = "Marry",
            price = 15,
            image = "https://fastly.picsum.photos/id/787/200/300.jpg?hmac=XItcL1ki66gQzP2FwRZjLbruiohUmaOYs9mmlDZe9KE",
            details = "Details_BBB"
        ),
        Book(
            id = "2",
            title = "BookC",
            author = "John",
            price = 20,
            image = "https://fastly.picsum.photos/id/814/200/300.jpg?hmac=Fdr3IdVM_oeEBSotkbzIZJ1ifzyImebBI2d7cIMNJLM",
            details = "Details_CCC"
        )
    )
}