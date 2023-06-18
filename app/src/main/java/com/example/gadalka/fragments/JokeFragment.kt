package com.example.gadalka.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.gadalka.Options
import com.example.gadalka.contract.navigator
import com.example.gadalka.databinding.FragmentJokeBinding

class JokeFragment : Fragment() {

    private lateinit var binding: FragmentJokeBinding

    private var options: Options? = null

    private var jokeViewModel: JokeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            options = it.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
        }
        jokeViewModel = ViewModelProvider(this).get(JokeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokeBinding.inflate(inflater, container, false)
        binding.exit.setOnClickListener { onExitPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokeViewModel?.fetchJokeData()

        jokeViewModel?.jokeLiveData?.observe(viewLifecycleOwner) { joke ->

            binding.tvJokePunchline.text = joke.punchline
            binding.tvJokeSetup.text = joke.setup
            binding.tvJokeType.text = joke.type
            // TODO: дописать обновление интерфейса
        }
    }
    private fun onExitPressed() {
        navigator().goBack()
    }


    companion object {
        private const val KEY_OPTIONS = "OPTIONS"

        fun newInstance(options: Options): JokeFragment {
            val fragment = JokeFragment()
            val args = Bundle().apply {
                putParcelable(KEY_OPTIONS, options)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
