package ua.edu.cdu.boris.newnav2025.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.edu.cdu.boris.newnav2025.AppDestinations
import ua.edu.cdu.boris.newnav2025.AppViewModel
import ua.edu.cdu.boris.newnav2025.model.Author

@Composable
fun FavoritesScreen(
    modifier: Modifier,
    onDestinationChange: (AppDestinations) -> Unit,
    viewModel1: AppViewModel = viewModel()
) {
    //val isShowToast by viewModel1.isShowToast
    Column(modifier = modifier) {
        var str by rememberSaveable() { mutableStateOf("It is Favorites Screen") }
        val author1 by viewModel1.author.observeAsState(Author("", ""))

        Text(modifier = Modifier.fillMaxWidth().background(Color.Green),
            color = Color.Red,
            textAlign = TextAlign.Center,
            text = str)

        Row {
            Text(author1.name)
            Spacer(Modifier.width(5.dp))
            Text(author1.birthday)
        }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                str = "123456"
                viewModel1.updateAuthor()
            })
        { Text("BTN 1") }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel1.showToast()
            })
        { Text("Show Toast") }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                onDestinationChange(AppDestinations.HOME)
            })
        { Text("To Home Screen") }
    }
    val localContext = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel1.isShowToast.collect {
            if (it){
                Toast.makeText(localContext, "This is a Toast", Toast.LENGTH_LONG).show()
            }
        }
    }
}
