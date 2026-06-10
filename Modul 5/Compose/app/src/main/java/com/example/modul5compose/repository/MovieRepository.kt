package com.example.modul5compose.repository

import com.example.modul5compose.database.MovieDao
import com.example.modul5compose.database.MovieEntity
import com.example.modul5compose.model.Movie
import com.example.modul5compose.model.MovieResponse
import com.example.modul5compose.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class MovieRepository(private val movieDao: MovieDao) {

    val allMovies: Flow<List<MovieEntity>> = movieDao.getAllMovies()

    suspend fun refreshMovies() {
        try {
            val response = RetrofitInstance.api.getPopularMovies()
            val movies: List<Movie> = response.results

            val movieEntities = movies.map { item ->
                MovieEntity(
                    id = item.id,
                    title = item.title,
                    overview = item.overview,
                    posterPath = item.posterPath,
                    releaseDate = item.releaseDate,
                    rating = item.rating
                )
            }

            movieDao.deleteAllMovies()
            movieDao.insertMovies(movieEntities)
            Timber.d("Repository: Sync Berhasil")
        } catch (e: Exception) {
            Timber.e("Repository: Sync Gagal \${e.message}")
        }
    }
}