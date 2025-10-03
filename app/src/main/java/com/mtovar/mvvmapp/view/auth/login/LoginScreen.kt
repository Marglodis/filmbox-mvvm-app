package com.mtovar.mvvmapp.view.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mtovar.mvvmapp.R
import com.mtovar.mvvmapp.ui.theme.MerriweatherSans


@Composable
fun LoginScreen() {
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


            var user by remember { mutableStateOf("") }
            TextField(
                value = user, onValueChange = { user = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15)
            )

            Spacer(modifier = Modifier.height(16.dp))
            var password by remember { mutableStateOf("") }
            TextField(
                value = password, onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ), onClick = { /*TODO*/ }) {
                Text(
                    text = "Iniciar Sesión",
                    color = MaterialTheme.colorScheme.background,
                    fontFamily = MerriweatherSans,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.weight(4f))


        }

    }


}