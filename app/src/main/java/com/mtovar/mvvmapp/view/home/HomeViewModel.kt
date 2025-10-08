package com.mtovar.mvvmapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtovar.mvvmapp.BuildConfig
import com.mtovar.mvvmapp.data.model.Movie
import com.mtovar.mvvmapp.data.remote.RetroFitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val response = RetroFitClient.apiService.getPopularMovies(
                    authorization = "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}"
                )

                _uiState.update { it.copy(movies = response.results, errorMessage = null) }


            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message ?: "Error al cargar películas") }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }

        }
    }

    fun retry() {
        fetchPopularMovies()
    }

}

data class HomeUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)