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
import com.example.gadalka.databinding.FragmentBoredBinding

class BoredFragment : Fragment() {

    private lateinit var binding: FragmentBoredBinding


    private var boredViewModel: BoredViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boredViewModel = ViewModelProvider(this)[BoredViewModel::class.java]
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

        boredViewModel?.fetchBoredData()

        boredViewModel?.boredLiveData?.observe(viewLifecycleOwner) { bored ->
            with(binding) {
                tvAccessibility.text = bored.accessibility.toString()
                tvActivity.text = bored.activity
                tvType.text = bored.type
                tvPrice.text = bored.price.toString()
                tvParticipants.text = bored.participants.toString()
                tvLink.text = bored.link
                tvKey.text = bored.key
            }
        }
    }
    private fun onExitPressed() {
        navigator().goBack()
    }

}
