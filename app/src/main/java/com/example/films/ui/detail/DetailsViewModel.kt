package com.example.films.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.films.AppState
import com.example.films.model.entities.Film
import com.example.films.model.repository.DetailsRepository
import com.example.films.model.repository.DetailsRepositoryImpl
import com.example.films.model.repository.RemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl(
        RemoteDataSource()
    )
) : ViewModel() {
    fun getLiveData() = detailsLiveData

    fun getFilmFromRemoteSource(id: Long) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getFilmDetailFromServer(id, callback)
    }

    private val callback = object : Callback<Film> {

        override fun onResponse(call: Call<Film>, response: Response<Film>) {
            val serverResponse: Film? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<Film>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    private fun checkResponse(serverResponse: Film): AppState {
        return AppState.Success(listOf(serverResponse))
    }
}