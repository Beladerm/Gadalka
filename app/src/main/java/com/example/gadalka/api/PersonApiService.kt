package com.example.gadalka.api

import com.example.gadalka.model.source.Age
import com.example.gadalka.model.source.Gender
import com.example.gadalka.model.source.Nationality
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderApiService {
    @GET("https://api.genderize.io")
    fun getGenderData(@Query("name") name: String): Observable<Gender>
}

interface AgeApiService {
    @GET("https://api.agify.io")
    fun getAgeData(@Query("name") name: String): Observable<Age>
}

interface NationalityApiService {
    @GET("https://api.nationalize.io")
    fun getNationalityData(@Query("name") name: String): Observable<Nationality>
}

