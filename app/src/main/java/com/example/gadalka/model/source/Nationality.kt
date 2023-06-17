package com.example.gadalka.model.source

data class Nationality(
    val country: List<Country>,
    val name: String
)

data class Country(
    val countryId: String,
    val probability: Double
)

