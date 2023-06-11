package com.example.gadalka.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gadalka.Joke
import com.example.gadalka.Options

class MenuFragment: Fragment() {

    private lateinit var options: Options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options = savedInstanceState?.getParcelable("") ?: Options.DEFAULT
    }

}