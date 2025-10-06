package com.mtovar.mvvmapp.view.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUserChanged(user: String) {
        // Esto es igual a _uiState.update { it.copy(user = user) }
        //_uiState.update { _uiState.value.copy(user = user)}
        _uiState.value = _uiState.value.copy(user = user)

        verifyInputText()
    }

    fun onPasswordChanged(password: String) {
        //_uiState.value = _uiState.value.copy(password = password)
        _uiState.update { it.copy(password = password) }

        verifyInputText()
    }

    fun verifyInputText() {
        val enableLogin =
            isUserValid(_uiState.value.user) && isPasswordValid(_uiState.value.password)
        _uiState.update { it.copy(isLoginEnabled = enableLogin) }
    }

    fun isUserValid(user: String): Boolean {
        return user == "admin"
    }

    fun isPasswordValid(password: String): Boolean {
        return password == "123456"
    }

}

data class LoginUiState(
    val user: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val isLoading: Boolean = false
)