package com.example.modul5compose.network
import com.example.modul5compose.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "0024904222b13a6e30001e304b419b05",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieResponse
}