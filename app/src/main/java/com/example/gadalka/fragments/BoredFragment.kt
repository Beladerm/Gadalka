package com.example.gadalka.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gadalka.Options
import com.example.gadalka.contract.navigator
import com.example.gadalka.databinding.FragmentBoredBinding

class BoredFragment : Fragment() {

    private lateinit var binding: FragmentBoredBinding

    private var options: Options? = null

    private val boredViewModel: BoredViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            options = it.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoredBinding.inflate(inflater, container, false)
        binding.exit.setOnClickListener { onExitPressed() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boredViewModel.boredLiveData.observe(viewLifecycleOwner) { bored ->
            binding.tvAccessibility.text = bored.accessibility.toString()
            binding.tvActivity.text = bored.activity
            binding.tvType.text = bored.type
            binding.tvPrice.text = bored.price.toString()
            binding.tvParticipants.text = bored.participants.toString()
            binding.tvLink.text = bored.link
            binding.tvKey.text = bored.key
            boredViewModel.fetchBoredData()
        }
    }
    private fun onExitPressed() {
        navigator().goBack()
    }

    companion object {
        private const val KEY_OPTIONS = "OPTIONS"

        fun newInstance(options: Options): BoredFragment {
            val fragment = BoredFragment()
            val args = Bundle().apply {
                putParcelable(KEY_OPTIONS, options)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
