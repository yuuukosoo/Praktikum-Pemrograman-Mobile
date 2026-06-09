package com.example.modul5compose.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Movie(
    val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("release_date") val releaseDate: String?,
    @SerialName("vote_average") val rating: Double
) {
    fun getFullPosterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
}
