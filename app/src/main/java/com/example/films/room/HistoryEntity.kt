package com.example.films.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val filmId: Long,
    val title: String,
    val posterPath: String
)
