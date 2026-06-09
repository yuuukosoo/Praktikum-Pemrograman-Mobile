package com.example.modul5compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5compose.database.MovieEntity
import com.example.modul5compose.repository.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository, private val username: String) : ViewModel() {

    val movies: StateFlow<List<MovieEntity>> = repository.allMovies
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init { refreshData() }

    fun refreshData() {
        viewModelScope.launch { repository.refreshMovies() }
    }
}