package com.example.gadalka.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gadalka.api.AgeApiService
import com.example.gadalka.api.ApiFactory
import com.example.gadalka.api.GenderApiService
import com.example.gadalka.api.NationalityApiService
import com.example.gadalka.model.Person
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PersonViewModel : ViewModel() {

    private val genderApiService: GenderApiService by lazy { ApiFactory.getGenderApiService() }
    private val ageApiService: AgeApiService by lazy { ApiFactory.getAgeApiService() }
    private val nationalityApiService: NationalityApiService by lazy { ApiFactory.getNationalityApiService() }

    private val _personLiveData = MutableLiveData<Person>()
    val personLiveData: LiveData<Person> = _personLiveData

    private val compositeDisposable = CompositeDisposable()



    fun fetchPersonData(name: String) {
        val genderObservable = genderApiService.getGenderData(name)
        val ageObservable = ageApiService.getAgeData(name)
        val nationalityObservable = nationalityApiService.getNationalityData(name)


        val disposable = Observable.zip(
            genderObservable,
            ageObservable,
            nationalityObservable
        ) { gender, age, nationality ->
            Person(
                name,
                nationality.country.firstOrNull()?.countryId ?: "VALHALLA",
                age.age,
                gender.gender
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { person -> _personLiveData.value = person },
                {
                    Log.d("PersonViewModel", "Error: ${it.message}")
                }
            )
        compositeDisposable.add(disposable)
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
