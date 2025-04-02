package com.example.discord.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.discord.data.entities.User
import com.example.discord.data.MyApplication

@Composable
fun RegisterView(
    navController: NavController
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RegisterCard(innerPadding, navController)
        }
    }
}

@Composable
private fun RegisterCard(innerPadding: PaddingValues, navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(0.75f)
    ) {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InformationView(navController)
            }
        }
    }
}

@Composable
private fun InformationView(navController: NavController) {
    val emailIsError = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }

    val displayNameIsError = remember { mutableStateOf(false) }
    val displayName = remember { mutableStateOf("") }

    val usernameIsError = remember { mutableStateOf(false) }
    val username = remember { mutableStateOf("") }

    val passwordIsError = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Create an account", fontSize = 24.sp, fontWeight = FontWeight.W900)

        TextFieldView(email, "Email", emailIsError)
        TextFieldView(displayName, "Display", displayNameIsError)
        TextFieldView(username, "Username", usernameIsError)
        TextFieldView(password, "Password", passwordIsError)

        Column {
            Button (
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    if (email.value.isEmpty() || username.value.isEmpty() || password.value.isEmpty()) {
                        emailIsError.value = email.value.isEmpty()

                        usernameIsError.value = username.value.isEmpty()

                        passwordIsError.value = password.value.isEmpty()

                    } else {
                        val newUser = User(
                            email = email.value,
                            displayName = displayName.value,
                            username = username.value,
                            password = password.value
                        )
                        createAccount(navController, newUser)
                    }

                }
            ) { Text("Continue") }
            Text(
                modifier = Modifier.clickable { navController.popBackStack() },
                text = "Already have an account?",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

private fun createAccount(navController: NavController, user: User) {
    MyApplication.database?.userDao()?.insertUser(
        User(
            email = user.email,
            displayName = user.displayName,
            username = user.username,
            password = user.password
        )
    )
    navController.popBackStack()
}

@Composable
private fun TextFieldView(
    values: MutableState<String>,
    text: String,
    isError: MutableState<Boolean>
){
    OutlinedTextField (
        modifier = Modifier.fillMaxWidth(),
        isError = isError.value,
        value = values.value,
        onValueChange = { values.value = it },
        placeholder = { Text(text) },
    )
}
