package com.mtovar.mvvmapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A singleton object for creating and managing a Retrofit instance.
 */
object RetroFitClient {

    /**
     * The base URL for the The Movie Database API.
     */
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    /**
     * The Retrofit instance, created lazily.
     */
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * The ApiService instance, created lazily from the Retrofit instance.
     */
    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
