package com.example.films.data

data class Film(
    val id: Long,
    val title: String,
    val overView: String,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double,
    val poster_path: String,
    val release_date: String
)