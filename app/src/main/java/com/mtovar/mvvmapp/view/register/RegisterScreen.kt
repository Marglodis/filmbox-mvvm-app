package com.mtovar.mvvmapp.view.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mtovar.mvvmapp.R

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    navigateToLogin: () -> Unit
){
    Scaffold { padding ->
        Column(
            Modifier
            .padding(padding)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.weight(1f))
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(R.drawable.logo_app),
                contentDescription = "Logo de la App"
            )
            Text(text = "Regístrate para acceder\na tu caja de películas",
                textAlign = TextAlign.Center,
                fontSize = 20.sp)


            Spacer(Modifier.weight(1f))

            FormRegisterParent()

            ButtonsRegister()

            Spacer(Modifier.weight(1f))
            Text(text = "Al registrate, aceptas nuestras\nCondiciones y Política de privacidad",
                Modifier.padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light)
            HorizontalDivider()

            ButtonsFoot(navigateToLogin)

        }

    }

}


@Composable
fun ButtonsRegister(){

    Column (Modifier.fillMaxWidth()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.background
            )
        ) {
            Text("Registrar")
        }

    }
}

@Composable
fun ButtonsFoot(navigateToLogin: () -> Unit){

    Row (Modifier.width(300.dp)
        .padding(20.dp),
        horizontalArrangement = Arrangement.Center) {
        TextButton(onClick = {navigateToLogin}) {
            Text("¿Tienes una cuenta?")
        }
        TextButton(onClick = {}) {
            Text("Entrar", fontWeight = FontWeight.Bold)
        }

    }
}
@Composable

fun FormRegisterParent() {
    var phoneNumberOrEmail by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            value = phoneNumberOrEmail,
            onValueChange = { phoneNumberOrEmail = it },
            label = "Número de móvil"
        )

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nombre completo"
        )

        CustomTextField(
            value = user,
            onValueChange = { user = it },
            label = "Nombre de usuario"
        )

        CustomPasswordField(
            password = password,
            onPasswordChange = { password = it }
        )
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
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
}

@Composable
fun CustomPasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    label: String = "Contraseña",
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (passwordVisible) R.drawable.visibility_off else R.drawable.visibility
            val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

            Icon(
                painter = painterResource(id = icon),
                contentDescription = description,
                tint = MaterialTheme.colorScheme.background, // color del icono
                modifier = Modifier.clickable {
                    passwordVisible = !passwordVisible
                }
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = Color.White,
            focusedTextColor = MaterialTheme.colorScheme.background,
            unfocusedTextColor = MaterialTheme.colorScheme.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.background,
            focusedLabelColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
            unfocusedLabelColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f)
        )
    )
}
