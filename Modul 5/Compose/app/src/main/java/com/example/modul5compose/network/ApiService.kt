package com.example.modul5compose.network

import com.example.modul5compose.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse
}