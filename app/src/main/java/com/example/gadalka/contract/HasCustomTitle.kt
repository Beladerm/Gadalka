package com.example.gadalka.contract

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitle(): Int
}