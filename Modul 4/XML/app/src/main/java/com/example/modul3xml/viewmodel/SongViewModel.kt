package com.example.modul3xml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.modul3xml.data.Song
import com.example.modul3xml.data.SongData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class SongViewModel(private val username: String) : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    private val _selectedSongId = MutableStateFlow<Int?>(null)
    val selectedSongId: StateFlow<Int?> = _selectedSongId.asStateFlow()

    private val _navigateToUrl = MutableStateFlow<String?>(null)
    val navigateToUrl: StateFlow<String?> = _navigateToUrl.asStateFlow()



    init {
        Timber.d("User: $username - Menginisialisasi ViewModel")

        _songs.value = SongData.SongList

        Timber.d("Data lagu berhasil masuk ke dalam list: ${_songs.value} item")
    }

    fun onSongDetailClicked(song: Song){
        Timber.d("Tombol Detail ditekan untuk lagu: ${song.title} (ID: ${song.id})")
        _selectedSongId.value = song.id
    }

    fun clearSelection(){
        _selectedSongId.value = null
    }

    fun onBrowserClicked(url: String){
        Timber.d("Tombol Explicit Intent ditekan untuk URL: $url")
        _navigateToUrl.value = url
    }

    fun clearUrlNavigation(){
        _navigateToUrl.value = null
    }
}