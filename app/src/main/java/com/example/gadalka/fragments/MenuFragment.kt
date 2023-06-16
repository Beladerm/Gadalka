package com.example.gadalka.fragments

import OptionsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gadalka.Options
import com.example.gadalka.contract.navigator
import com.example.gadalka.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    private lateinit var options: Options
    private val optionsViewModel: OptionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        options = savedInstanceState?.getParcelable("") ?: Options.DEFAULT
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        optionsViewModel.name.observe(viewLifecycleOwner) { name ->
            options = options.copy(name = name)
        }
//
//        navigator().listenResult(Options::class.java, viewLifecycleOwner) {
//            options = it
//        }
        with(binding) {

            randomButton.setOnClickListener {
                val name = editTextTextPersonName.text.toString().trim()

                if (name.isNotBlank()) {
                    onBasePressed(name)
                } else {
                    editTextTextPersonName.error = "Введите имя"
                }
            }

            boredButton.setOnClickListener {
                val name = editTextTextPersonName.text.toString().trim()

                if (name.isNotBlank()) {
                    onBoredPressed(name)
                } else {
                    editTextTextPersonName.error = "Введите имя"
                }
            }

            jokeButton.setOnClickListener {
                val name = editTextTextPersonName.text.toString().trim()

                if (name.isNotBlank()) {
                    onJokePressed(name)
                } else {
                    editTextTextPersonName.error = "Введите имя"
                }
            }


            return root
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    private fun onExitPressed() {
        navigator().goBack()
    }
    private fun onJokePressed(name: String) {
        optionsViewModel.setName(name)
        navigator().showJokeScreen(options)
    }

    private fun onBoredPressed(name: String) {
        optionsViewModel.setName(name)
        navigator().showBoredScreen(options)
    }

    private fun onBasePressed(name: String) {
        optionsViewModel.setName(name)
        navigator().showBaseScreen(options)
    }

    companion object {
        private const val KEY_OPTIONS = "OPTIONS"
    }
}