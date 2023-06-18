package com.example.gadalka.api

import com.example.gadalka.model.Bored
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BoredApiService {
    @GET("activity")
    fun getBoredData(): Single<Bored>
}