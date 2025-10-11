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

/**
 * ViewModel for the home screen.
 */
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    /**
     * The UI state for the home screen.
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchPopularMovies()
    }

    /**
     * Fetches popular movies from the API.
     * @param page The page to fetch.
     */
    fun fetchPopularMovies(page: Int = 1) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = page == 1) }
            try {
                val response = RetroFitClient.apiService.getPopularMovies(
                    authorization = "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN}",
                    page = page
                )

                _uiState.update {
                    it.copy(
                        // SI es la página 1 vamos a reemplazar, si no agregar a la lista existente
                        movies = if (page == 1) response.results
                        else it.movies + response.results,
                        currentPage = page,
                        hasMorePages = page < response.totalPages,
                        errorMessage = null,
                        isLoadingMore = false
                    )
                }


            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message ?: "Error al cargar películas") }
            }
            _uiState.update {
                it.copy(isLoading = false)
            }

        }
    }

    /**
     * Loads the next page of popular movies.
     */
    fun loadNextPage() {
        if (!_uiState.value.isLoadingMore && _uiState.value.hasMorePages) {
            _uiState.update { it.copy(isLoadingMore = true) }
            fetchPopularMovies(_uiState.value.currentPage + 1)
        }

    }

    /**
     * Retries fetching popular movies.
     */
    fun retry() {
        _uiState.update { it.copy(errorMessage = null, currentPage = 1, isLoadingMore = false) }
        fetchPopularMovies()
    }

}

/**
 * Represents the UI state for the home screen.
 * @property isLoading Whether the screen is currently loading.
 * @property movies The list of popular movies.
 * @property errorMessage The error message, if any.
 * @property hasMorePages Whether there are more pages to load.
 * @property currentPage The current page.
 * @property isLoadingMore Whether more movies are being loaded.
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null,
    val hasMorePages: Boolean = true,
    val currentPage: Int = 1,
    val isLoadingMore: Boolean = false
)
