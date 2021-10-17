package com.example.films.model.entities

data class FilmDTO(
    val results: List<Film>
)

data class Film(
    val id: Long?,
    val title: String?,
    val overView: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?
)