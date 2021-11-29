package com.example.films.model.repository

import com.example.films.model.entities.Film

interface LocalRepository {
    fun getAllHistory(): List<Film>
    fun saveEntity(film: Film)
}