package com.example.films

import com.example.films.model.repository.Repository
import com.example.films.model.repository.RepositoryImpl
import com.example.films.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> {
        RepositoryImpl()
    }

    viewModel {
        HomeViewModel(get())
    }
}