package com.example.gadalka.fragments

import OptionsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gadalka.Options
import com.example.gadalka.contract.navigator
import com.example.gadalka.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    private lateinit var options: Options
    private var optionsViewModel: OptionsViewModel? = null
    private lateinit var binding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        optionsViewModel = ViewModelProvider(this)[OptionsViewModel::class.java]

        options = savedInstanceState?.getParcelable("") ?: Options.DEFAULT

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        optionsViewModel?.name?.observe(viewLifecycleOwner) { name ->
            options = options.copy(name = name)
        }

        with(binding) {

            randomButton.setOnClickListener {
                onButtonPressed { name ->
                    onBasePressed(name) }
            }

            boredButton.setOnClickListener {
                 onBoredPressed()
            }

            jokeButton.setOnClickListener {
                onJokePressed()
            }

            exit.setOnClickListener { onExitPressed() }


            return root


        }
    }

    private fun onButtonPressed(onPressed: (String) -> Unit) {
        val name = binding.editTextTextPersonName.text.toString().trim()

        if (name.isNotBlank()) {
            onPressed(name)
        } else {
            binding.editTextTextPersonName.error = "Введите имя"
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    private fun onExitPressed() {
        navigator().goBack()
    }
    private fun onJokePressed () {
        navigator().showJokeScreen()
    }

    private fun onBoredPressed() {
        navigator().showBoredScreen()
    }

    private fun onBasePressed(name: String) {
        optionsViewModel?.setName(name)
        navigator().showBaseScreen(options)
    }



    companion object {
        private const val KEY_OPTIONS = "OPTIONS"
    }
}