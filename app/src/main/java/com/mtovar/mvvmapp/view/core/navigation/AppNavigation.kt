package com.mtovar.mvvmapp.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mtovar.mvvmapp.view.auth.login.LoginScreen
import com.mtovar.mvvmapp.view.home.HomeScreen
import com.mtovar.mvvmapp.view.register.RegisterScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> { LoginScreen(
            navigateToRegister = { navController.navigate(Register) },
            navigateToHome = { navController.navigate(Home) }
        ) }

        composable<Register> { RegisterScreen(
            navigateToLogin = { navController.popBackStack() }
        ) }

        composable<Home> { HomeScreen() }

    }
}