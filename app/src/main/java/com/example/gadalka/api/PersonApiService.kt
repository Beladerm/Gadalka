package com.example.gadalka.api

import com.example.gadalka.model.source.Age
import com.example.gadalka.model.source.Gender
import com.example.gadalka.model.source.Nationality
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderApiService {
    @GET("https://api.genderize.io") // TODO: Change this to the correct endpoint
    fun getGenderData(@Query("name") name: String): Single<Gender>
}

interface AgeApiService {
    @GET("https://api.agify.io") // TODO: Change this to the correct endpoint
    fun getAgeData(@Query("name") name: String): Single<Age>
}

interface NationalityApiService {
    @GET("https://api.nationalize.io") // TODO: Change this to the correct endpoint
    fun getNationalityData(@Query("name") name: String): Single<Nationality>
}

