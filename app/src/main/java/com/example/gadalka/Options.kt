package com.example.gadalka

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Options(
    val name: String,
) : Parcelable {

    companion object {
        @JvmStatic val DEFAULT = Options(
            name = "NIGGERS",
        )
    }
}