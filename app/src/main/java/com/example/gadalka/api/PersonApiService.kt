package com.example.gadalka.api

import com.example.gadalka.model.Person
import com.example.gadalka.model.source.Age
import com.example.gadalka.model.source.Gender
import com.example.gadalka.model.source.Nationality
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApiService {
    @GET("gender-endpoint")
    fun getGenderData(@Query("name") name: String): Single<Gender>

    @GET("age-endpoint")
    fun getAgeData(@Query("name") name: String): Single<Age>

    @GET("nationality-endpoint")
    fun getNationalityData(@Query("name") name: String): Single<Nationality>
}
