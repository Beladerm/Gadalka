package com.example.gadalka.api

import com.example.gadalka.ApiConstants
import com.example.gadalka.api.adapters.NationalityAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiFactory {

    companion object {

        private val moshi: Moshi = Moshi.Builder()
            .add(NationalityAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

        private val nationalityRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.NATIONALITY_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        private var genderApiService: GenderApiService? = null
        private var ageApiService: AgeApiService? = null
        private var nationalityApiService: NationalityApiService? = null

        private var jokeApiService: JokeApiService? = null
        private var boredApiService: BoredApiService? = null

        private fun createRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        fun getJokeApiService(): JokeApiService {
            if (jokeApiService == null) {
                val retrofit = createRetrofit(ApiConstants.JOKE_URL)
                jokeApiService = retrofit.create(JokeApiService::class.java)
            }
            return jokeApiService!!
        }

        fun getBoredApiService(): BoredApiService {
            if (boredApiService == null) {
                val retrofit = createRetrofit(ApiConstants.BORED_URL)
                boredApiService = retrofit.create(BoredApiService::class.java)
            }
            return boredApiService!!
        }

        fun getGenderApiService(): GenderApiService {
            if (genderApiService == null) {
                val retrofit = createRetrofit(ApiConstants.GENDER_URL)
                genderApiService = retrofit.create(GenderApiService::class.java)
            }
            return genderApiService!!
        }

        fun getAgeApiService(): AgeApiService {
            if (ageApiService == null) {
                val retrofit = createRetrofit(ApiConstants.AGE_URL)
                ageApiService = retrofit.create(AgeApiService::class.java)
            }
            return ageApiService!!
        }

        fun getNationalityApiService(): NationalityApiService {
            if (nationalityApiService == null) {
                nationalityApiService = nationalityRetrofit.create(NationalityApiService::class.java)
            }
            return nationalityApiService!!
        }
    }
}
