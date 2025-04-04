package com.example.discord.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.example.discord.data.MyApplication
import com.example.discord.Screen
import com.example.discord.data.entities.User

@Composable
fun LogInView (
    navController: NavController
) {
    MyApplication.database?.userDao()?.deleteUserById(1)
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CardView(innerPadding, navController) }
    }
}

@Composable
private fun CardView(innerPadding: PaddingValues, navController: NavController) {
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
                TextWelcome()
                InformationView(navController)
            }
        }
    }
}

@Composable
private fun TextWelcome() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome back!",
            fontSize = 24.sp,
            fontWeight = FontWeight.W900
        )
        Text(
            text = "We're excited to see you again!",
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun InformationView(navController: NavController) {
    val emailIsError = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }
    val passwordIsError = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf("") }

    val textForgotModifier: Modifier =
        Modifier.clickable {
            emailIsError.value = email.value.isEmpty()
            passwordIsError.value = false
        }

    val textRegisterModifier: Modifier =
        Modifier.clickable { navController.navigate(route = Screen.registerView.route) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextFieldView(email, "Email", emailIsError)

        Column {
            TextFieldView(password, "Password", passwordIsError)
            ClickableText(textForgotModifier, "Forgot your password?")
        }

        Column {
            Button (
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (email.value.isEmpty() && password.value.isEmpty()) {
                        emailIsError.value = false
                        passwordIsError.value = false
                    } else {
                        if (email.value.isNotEmpty() && password.value.isEmpty()) {
                            emailIsError.value = true
                            passwordIsError.value = true
                        } else {
                            emailIsError.value = false
                            passwordIsError.value = false

                            LogInUser(email.value, password.value, navController)

                        }
                    }
                }
            ) { Text("Log In") }
            Row {
                Text(text = "Need an account? ")
                ClickableText(textRegisterModifier, "Register")
            }
        }
    }
}

private fun LogInUser(email: String, password: String, navController: NavController) {
    var user: User? = MyApplication.database?.userDao()?.findUserByEmail(email, password)

    if (user != null) {
        if (
            email == user.email &&
            password == user.password
            ) { navController.navigate(route = "${Screen.menuView.route}/${user.id}") }
    }

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
        placeholder = {
            Text(text)
        },
    )
}

@Composable
private fun ClickableText(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        color = MaterialTheme.colorScheme.primary
    )
}
