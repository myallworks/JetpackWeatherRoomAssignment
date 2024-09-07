package dev.shrishri1108.jetpackweaterroomassignment

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.shrishri1108.jetpackweaterroomassignment.viewmodels.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("userlist") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    val passwordError = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,  // Animate across a wide range
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color.Cyan, Color.Blue),
                    start = Offset(animatedOffset, animatedOffset),
                    end = Offset(0f, 1000f)
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Email Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(12.dp),
            isError = emailError.value.isNotEmpty()
        )
        if (emailError.value.isNotEmpty()) {
            Text(
                text = emailError.value,
                color = Color.Red,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = passwordError.value.isNotEmpty()
        )
        if (passwordError.value.isNotEmpty()) {
            Text(
                text = emailError.value,
                color = Color.Red,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            var isValid = true
            if (password.value.isBlank()) {
                passwordError.value = "Password cannot be empty"
                isValid = false
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username.value).matches()) {
                emailError.value = "Invalid email address"
                isValid = false
            }
            if (isValid) {
                viewModel.login(username.value, password.value) {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("You are not authourised to acces it.")
                    }
                }
            }
        }) {
            Text("Login")
        }
    }

}


