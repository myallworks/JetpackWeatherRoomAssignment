package dev.shrishri1108.jetpackweaterroomassignment.theme.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.ListItem
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.shrishri1108.jetpackweaterroomassignment.data.UsersImp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {

    val users = viewModel.usersUiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "User List",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White
                        )
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate("userform") }) {
                        Icon(Icons.Default.Add, contentDescription = "Add User")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF1976D2)
                )
            )
        },
        content = {
            when (users) {
                is UsersUiState.Error -> {

                }

                is UsersUiState.Loading -> {
                    showProgressBar()
                }

                is UsersUiState.Success -> {

                    val data = users.data
                    LazyColumn {
                        items(data) { user ->
                            SwipeToDelete(user, viewModel, navController)
                        }
                    }
                }

            }
        }
    )

}

@Composable
fun showProgressBar() {
    var progress by remember { mutableStateOf(0.7f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(progress = progress)

        Spacer(modifier = Modifier.height(16.dp))

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDelete(user: UsersImp, viewModel: HomeViewModel, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val dismissState = rememberDismissState(
        confirmStateChange = { dismissValue ->
            if (dismissValue == DismissValue.DismissedToStart) {
                viewModel.deleteUser(user)
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("${user.firstName} deleted")
                }

                true
            } else {
                false
            }
        }
    )

    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
        dismissThresholds = { FractionalThreshold(0.5f) },
        background = {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
            }
        },
        dismissContent = {
            ListItem(
                text = { Text("${user.firstName} ${user.lastName}") },
                secondaryText = { Text(user.email) },
                modifier = Modifier.clickable {
                    navController.navigate("weather")
                }
            )
        }
    )
}
