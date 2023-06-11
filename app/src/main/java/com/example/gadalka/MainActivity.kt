package com.example.gadalka

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gadalka.fragments.MenuFragment
import ru.ms.stu.todolist_va.R
import ru.ms.stu.todolist_va.databinding.ActivityMainBinding

const val bored = "https://www.boredapi.com/api/activity"
const val joke = "https://official-joke-api.appspot.com/random_joke"

const val gender = "https://api.genderize.io?name="
const val age = "https://api.agify.io?name="
const val nationality = "https://api.nationalize.io?name="


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentCreated(fm, f, savedInstanceState)
            updateUI()
        }
    }

    private fun updateUI() {
        val fragment = currentFragment
        // TODO: update UI


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityMainBinding
            .inflate(layoutInflater)
            .also { setContentView(it.root) }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MenuFragment())
                .commit()
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)

    }
}
