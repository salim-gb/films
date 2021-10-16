package com.example.films.model.repository

import com.example.films.model.entities.Film

interface Repository {
    fun getFilmsFromServer() : List<Film>
    fun getFilmsFromLocalStorage() : List<Film>
}