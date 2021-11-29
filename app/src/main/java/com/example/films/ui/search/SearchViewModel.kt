package com.example.films.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.AppState
import com.example.films.model.entities.Film
import com.example.films.model.entities.FilmDTO
import com.example.films.model.repository.RemoteDataSource
import com.example.films.model.repository.SearchRemoteDataSource
import com.example.films.model.repository.SearchRepository
import com.example.films.model.repository.SearchRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"

class SearchViewModel(
    private val searchLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val searchRepositoryImpl: SearchRepository = SearchRepositoryImpl(
        SearchRemoteDataSource()
    )
) : ViewModel() {
    fun getLiveData() = searchLiveData

    fun getFilmsFromRemoteSource(query: String) {
        searchLiveData.value = AppState.Loading
        searchRepositoryImpl.getSearchFilmFromServer(query, callback)
    }

    private val callback = object : Callback<Film> {
        override fun onResponse(call: Call<Film>, response: Response<Film>) {
            val serverResponse: Film? = response.body()
            searchLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Film>, t: Throwable) {
            searchLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: Film): AppState {
        return AppState.Success(listOf(serverResponse))
    }
}