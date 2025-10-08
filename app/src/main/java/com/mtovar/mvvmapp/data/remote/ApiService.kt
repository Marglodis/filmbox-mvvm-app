package com.mtovar.mvvmapp.data.remote

import com.mtovar.mvvmapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MovieResponse
}
