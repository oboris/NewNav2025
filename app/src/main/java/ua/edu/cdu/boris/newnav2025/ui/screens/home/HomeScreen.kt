package ua.edu.cdu.boris.newnav2025.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.edu.cdu.boris.newnav2025.AppViewModel
import ua.edu.cdu.boris.newnav2025.model.Author
import ua.edu.cdu.boris.newnav2025.model.AuthorList
import ua.edu.cdu.boris.newnav2025.model.Book

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel1: AppViewModel = viewModel()
) {
    val myMegaList by viewModel1.myMegaList.observeAsState(emptyList())
//    SideEffect {
//        viewModel1.getAllData()
//    }
    Column(modifier = modifier) {
        LaunchedEffect(Unit) {
            viewModel1.getAllData()
        }

        Text(text = "It is Home Screen")

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1.0f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(items = myMegaList) { listItem ->
                when (listItem) {
                    is Author -> MyMegaAuthorItem(listItem)
                    is Book -> MyMegaBookItem(listItem)
                    is AuthorList -> MyMegaAuthorListItem(listItem)
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel1.loadAllDataToDb()
            }
        )
        { Text("Load Data to Data Base") }

        Button(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel1.clearAllDataFromDb()
            })
        { Text("Clear Data Base") }
    }
}


@Composable
fun MyMegaBookItem(book: Book) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        val mod = Modifier.weight(2f)
            .background(Color.Gray)
        Text(
            modifier = mod
                .padding(vertical = 50.dp),
            text = book.title,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.weight(1f),
            text = book.pages.toString()
        )

        Text(
            modifier = mod,
            text = book.author.name
        )

        Button(
            onClick = {

            })
        { Text("Del") }
    }
}

@Composable
fun MyMegaAuthorItem(author: Author) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .background(Color.Gray),
            text = author.name
        )

        Spacer(Modifier.width(10.dp))

        Text(
            modifier = Modifier
                .weight(1f)
                .background(Color.Gray),
            text = author.birthday
        )

        Button(
            onClick = {

            })
        { Text("Del") }
    }
}

@Composable
fun MyMegaAuthorListItem(authorList: AuthorList) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(authorList.authorList) {
            MyMegaAuthorItemForList(it)
        }
    }
}

@Composable
fun MyMegaAuthorItemForList(author: Author) {
    Column(
        Modifier
            .background(Color.Gray)
            .wrapContentWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .background(Color.Red),
            textAlign = TextAlign.Center,
            text = author.name
        )

        Spacer(Modifier.height(10.dp))

        Text(author.birthday)
    }
}
