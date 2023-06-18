package com.example.gadalka.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadalka.api.ApiFactory
import com.example.gadalka.api.BoredApiService
import com.example.gadalka.model.Bored
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BoredViewModel: ViewModel() {

    private val boredApiService: BoredApiService by lazy { ApiFactory.getBoredApiService() }

    private val _boredLiveData = MutableLiveData<Bored>()
    val boredLiveData: LiveData<Bored> = _boredLiveData

    private val compositeDisposable = CompositeDisposable()

    fun fetchBoredData() {
        val disposable = boredApiService.getBoredData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { bored -> _boredLiveData.postValue(bored) },
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