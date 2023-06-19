package com.example.gadalka.api.adapters

import com.example.gadalka.model.source.Country
import com.example.gadalka.model.source.Nationality
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class NationalityAdapter : JsonAdapter<Nationality>() {

    @FromJson
    override fun fromJson(reader: JsonReader): Nationality {
        var name = ""
        val countryList = mutableListOf<Country>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "name" -> name = reader.nextString()
                "country" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        var countryId = ""
                        var probability = 0.0

                        while (reader.hasNext()) {
                            when (reader.nextName()) {
                                "country_id" -> countryId = reader.nextString()
                                "probability" -> probability = reader.nextDouble()
                                else -> reader.skipValue()
                            }
                        }

                        val country = Country(countryId, probability)
                        countryList.add(country)
                        reader.endObject()
                    }
                    reader.endArray()
                }
                else -> reader.skipValue()
            }
        }
        reader.endObject()

        return Nationality(countryList, name)
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Nationality?) {
        throw UnsupportedOperationException("toJson() not implemented")
    }
}

