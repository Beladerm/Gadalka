package com.example.gadalka.api

import com.example.gadalka.model.Joke
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface JokeApiService {

    @GET("random_joke")
    fun getJokeData(): Single<Joke>
}

