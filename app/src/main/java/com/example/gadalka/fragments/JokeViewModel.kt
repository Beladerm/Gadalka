package com.example.gadalka.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadalka.api.ApiFactory
import com.example.gadalka.api.JokeApiService
import com.example.gadalka.model.Joke
import io.reactivex.rxjava3.disposables.CompositeDisposable

class JokeViewModel: ViewModel() {

    private val jokeApiService: JokeApiService by lazy { ApiFactory.getJokeApiService() }

    private val _jokeLiveData = MutableLiveData<Joke>()
    val jokeLiveData : LiveData<Joke> = _jokeLiveData

    private val compositeDisposable = CompositeDisposable()

    fun fetchJokeData() {
        val disposable = jokeApiService.getJokeData()
            .subscribe(
                { joke -> _jokeLiveData.value = joke },
                {
                    // Обработайте ошибку, если возникла

                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}