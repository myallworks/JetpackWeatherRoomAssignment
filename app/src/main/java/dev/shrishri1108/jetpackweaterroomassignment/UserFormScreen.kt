package dev.shrishri1108.jetpackweaterroomassignment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.shrishri1108.jetpackweaterroomassignment.data.UsersImp
import dev.shrishri1108.jetpackweaterroomassignment.theme.presentation.HomeViewModel

@Composable
fun UserFormScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    val firstNameError = remember { mutableStateOf("") }
    val lastNameError = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf("") }

    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoading.value = true
        kotlinx.coroutines.delay(500)
        isLoading.value = false
    }

    fun validateInputs(): Boolean {
        var isValid = true
        firstNameError.value = ""
        lastNameError.value = ""
        emailError.value = ""

        if (firstName.value.isBlank()) {
            firstNameError.value = "First name cannot be empty"
            isValid = false
        }

        if (lastName.value.isBlank()) {
            lastNameError.value = "Last name cannot be empty"
            isValid = false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            emailError.value = "Invalid email address"
            isValid = false
        }

        return isValid
    }

    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF1976D2))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add New User",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2)
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = firstName.value,
                onValueChange = { firstName.value = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                isError = firstNameError.value.isNotEmpty()
            )
            if (firstNameError.value.isNotEmpty()) {
                Text(
                    text = firstNameError.value,
                    color = Color.Red,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                label = { Text("Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                isError = lastNameError.value.isNotEmpty()
            )
            if (lastNameError.value.isNotEmpty()) {
                Text(
                    text = lastNameError.value,
                    color = Color.Red,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (validateInputs()) {
                            viewModel.insertUser(
                                UsersImp(
                                    firstName = firstName.value,
                                    lastName = lastName.value,
                                    email = email.value
                                )
                            )
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Save", color = Color.White)
                }

                Spacer(modifier = Modifier.width(16.dp))

                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
