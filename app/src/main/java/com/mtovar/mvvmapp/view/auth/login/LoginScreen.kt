package com.mtovar.mvvmapp.view.auth.login

import android.R.attr.label
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mtovar.mvvmapp.R
import com.mtovar.mvvmapp.ui.theme.MerriweatherSans


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(3f))
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "Logo"
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Tu biblioteca de películas favoritas",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = MerriweatherSans,
                fontWeight = FontWeight.Normal,
                //fontSize = 40.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            TextField(
                value = uiState.user, onValueChange = { loginViewModel.onUserChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15),
                singleLine = true,
                label = { Text("Nombre de usuario") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = MaterialTheme.colorScheme.background,
                    unfocusedTextColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent, // sin línea inferior
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.background,
                    focusedLabelColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                    unfocusedLabelColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = uiState.password, onValueChange = { loginViewModel.onPasswordChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15),
                singleLine = true,
                label = { Text("Contraseña") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = MaterialTheme.colorScheme.background,
                    unfocusedTextColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent, // sin línea inferior
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.background,
                    focusedLabelColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                    unfocusedLabelColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                enabled = uiState.isLoginEnabled,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary
                ), onClick = { navigateToHome() }) {
                Text(
                    text = "Iniciar Sesión",
                    color = MaterialTheme.colorScheme.background,
                    fontFamily = MerriweatherSans,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.weight(4f))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                ), onClick = { navigateToRegister() }) {
                Text(
                    text = "Registrarse",
                    color = MaterialTheme.colorScheme.background,
                    fontFamily = MerriweatherSans,
                    fontWeight = FontWeight.Normal
                )
            }
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen (navigateToRegister = {}, navigateToHome = {})
}

