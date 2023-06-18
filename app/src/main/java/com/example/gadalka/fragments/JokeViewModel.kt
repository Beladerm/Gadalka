package com.example.gadalka.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadalka.api.ApiFactory
import com.example.gadalka.api.JokeApiService
import com.example.gadalka.model.Joke
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class JokeViewModel: ViewModel() {

    private val jokeApiService: JokeApiService by lazy { ApiFactory.getJokeApiService() }

    private val _jokeLiveData = MutableLiveData<Joke>()
    val jokeLiveData : LiveData<Joke> = _jokeLiveData

    private val compositeDisposable = CompositeDisposable()

    fun fetchJokeData() {
        val disposable = jokeApiService.getJokeData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { joke -> _jokeLiveData.postValue(joke) },
                {
                    Log.d("JokeViewModel", "Error: ${it.message}")
                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}