package ua.edu.cdu.boris.newnav2025.ui.screens.favorite

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.edu.cdu.boris.newnav2025.AppDestinations
import ua.edu.cdu.boris.newnav2025.AppViewModel
import ua.edu.cdu.boris.newnav2025.model.Author

sealed class FavoriteNavigationGraph(val route: String, val description: String) {
    object FavoriteMain :
        FavoriteNavigationGraph(route = "favorite_main", description = "Favorite Main Screen")

    object FavoriteDetail :
        FavoriteNavigationGraph(route = "favorite_detail", description = "Favorite Detail Screen")
}

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    modifier: Modifier,
    onDestinationChange: (AppDestinations) -> Unit,
    viewModel: AppViewModel = viewModel()
) {
//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FavoriteNavigationGraph.FavoriteMain.route
    ) {
        composable(route = FavoriteNavigationGraph.FavoriteMain.route) {
            FavoriteMainScreen(
                modifier = modifier,
                viewModel = viewModel,
                onNavigateToDetail = {
                    navController.navigate(FavoriteNavigationGraph.FavoriteDetail.route)
                },
                onDestinationChange = onDestinationChange
            )
        }

        composable(route = FavoriteNavigationGraph.FavoriteDetail.route) {
            FavoriteDetailScreen(modifier = modifier)
        }
    }
    //val isShowToast by viewModel1.isShowToast

}

@Composable
fun FavoriteDetailScreen(
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green),
            color = Color.Red,
            textAlign = TextAlign.Center,
            text = "Favorite Detail Screen"
        )
    }
}

@Composable
fun FavoriteMainScreen(
    modifier: Modifier,
    viewModel: AppViewModel,
    onNavigateToDetail: () -> Unit,
    onDestinationChange: (AppDestinations) -> Unit
) {
    val isShowToastLiveData by viewModel.isShowToastLiveData.observeAsState()
    val isShowToastState by viewModel.isShowToastState

    Column(modifier = modifier) {
        var str by rememberSaveable { mutableStateOf("It is Favorites Screen") }
        val author1 by viewModel.author.observeAsState(Author("", ""))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green),
            color = Color.Red,
            textAlign = TextAlign.Center,
            text = str
        )

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
                viewModel.updateAuthor()
            })
        { Text("BTN 1") }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.showToastLiveData()
            })
        { Text("Show Toast LiveData") }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.showToastState()
            })
        { Text("Show Toast State") }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.showToastSharedFlow()
            })
        { Text("Show Toast SharedFlow") }

        Button(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            onClick = onNavigateToDetail
            )
        { Text("To Detail Screen") }

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

    if (isShowToastLiveData == true) {
        Toast.makeText(localContext, "This is a Toast from LiveData", Toast.LENGTH_LONG).show()
    }

    if (isShowToastState) {
        Toast.makeText(localContext, "This is a Toast from State", Toast.LENGTH_LONG).show()
    }

    LaunchedEffect(Unit) {
        viewModel.isShowToastSharedFlow.collect {
            if (it) {
                Toast.makeText(localContext, "This is a Toast from SharedFlow", Toast.LENGTH_LONG).show()
            }
        }
    }
}

