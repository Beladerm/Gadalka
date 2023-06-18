package com.example.gadalka.api

import com.example.gadalka.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    companion object {

        private var genderApiService: GenderApiService? = null
        private var ageApiService: AgeApiService? = null
        private var nationalityApiService: NationalityApiService? = null

        private var jokeApiService: JokeApiService? = null
        private var boredApiService: BoredApiService? = null

        fun getJokeApiService(): JokeApiService {
            if (jokeApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.JOKE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                jokeApiService = retrofit.create(JokeApiService::class.java)
            }
            return jokeApiService!!
        }

        fun getBoredApiService(): BoredApiService {
            if (boredApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.BORED_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                boredApiService = retrofit.create(BoredApiService::class.java)
            }
            return boredApiService!!
        }
        fun getGenderApiService(): GenderApiService {
            if (genderApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.GENDER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                genderApiService = retrofit.create(GenderApiService::class.java)
            }
            return genderApiService!!
        }

        fun getAgeApiService(): AgeApiService {
            if (ageApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.AGE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                ageApiService = retrofit.create(AgeApiService::class.java)
            }
            return ageApiService!!
        }

        fun getNationalityApiService(): NationalityApiService {
            if (nationalityApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(ApiConstants.NATIONALITY_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                nationalityApiService = retrofit.create(NationalityApiService::class.java)
            }
            return nationalityApiService!!
        }
    }
}
