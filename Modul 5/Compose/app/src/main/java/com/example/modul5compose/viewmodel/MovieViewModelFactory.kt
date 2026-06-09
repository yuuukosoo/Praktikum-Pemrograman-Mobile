package com.example.modul5compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5compose.repository.MovieRepository

class MovieViewModelFactory(private val repository: MovieRepository, private val username: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository, username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}