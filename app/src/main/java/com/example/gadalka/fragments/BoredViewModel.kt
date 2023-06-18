package com.example.gadalka.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadalka.api.ApiFactory
import com.example.gadalka.api.BoredApiService
import com.example.gadalka.model.Bored
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BoredViewModel: ViewModel() {

    private val boredApiService: BoredApiService by lazy { ApiFactory.getBoredApiService() }

    private val _boredLiveData = MutableLiveData<Bored>()
    val boredLiveData: LiveData<Bored> = _boredLiveData

    private val compositeDisposable = CompositeDisposable()

    fun fetchBoredData() {
        val disposable = boredApiService.getBoredData()
            .subscribe(
                { bored -> _boredLiveData.value = bored },
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