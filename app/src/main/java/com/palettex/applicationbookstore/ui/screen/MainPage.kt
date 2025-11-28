package com.palettex.applicationbookstore.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.palettex.applicationbookstore.data.MockData
import com.palettex.applicationbookstore.domain.model.Book

@Composable
fun MainPage(name: String, modifier: Modifier = Modifier) {
    val viewModel: MainPageViewModel = viewModel()
    val storeBook by viewModel.storeBook.collectAsState()

    Column(modifier = modifier) {
        Text(
            text = "Hello $name!"
        )
        AllBookView(
            books = storeBook,
            doSearch = { keyWord ->
                viewModel.search(keyWord)
            },
            doFilterPrice = { price ->
                viewModel.priceLessThan(price)
            },
            doClickFavorite = { id ->
                viewModel.toggleFavorite(id)
            }
        )
    }
}

@Composable
fun AllBookView(
    books: List<Book>,
    doSearch: (String) -> Unit,
    doFilterPrice: (Int) -> Unit,
    doClickFavorite: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("0") }

    LazyColumn() {
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    modifier = Modifier.testTag("tesTag_keyWord"),
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                        doSearch(newText)
                    }
                )
            }
            Row (modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    modifier = Modifier.testTag("tesTag_price"),
                    value = price,
                    onValueChange = { it ->
                        if (it.isDigitsOnly()) {
                            price = it
                        }
                    }
                )
                Button(
                    modifier = Modifier.testTag("tesTag_filter"),
                    onClick = {
                        if (price.isDigitsOnly() && price.isNotEmpty()) {
                            doFilterPrice(price.toInt())
                        }
                    },
                ) {
                    Text("filter!")
                }
            }
        }

        books.forEachIndexed { index, book ->
            item {
                OneBookView(book) { it ->
                    doClickFavorite(it)
                }
            }
        }
    }
}

@Composable
fun OneBookView(
    book: Book,
    clickFavorite: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            // Book cover image
            AsyncImage(
                model = book.image,
                contentDescription = book.title,
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Book details
            Column(
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(text = book.title)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = book.author)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "$${book.price}")
                Column(modifier = Modifier.clickable{
                    clickFavorite(book.id)
                }) {
                    if (book.isLike) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "like",
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    val test = MockData.sampleBooks
    Column(modifier = Modifier.fillMaxSize()) {
        AllBookView(test, {}, {} , {})
    }
}