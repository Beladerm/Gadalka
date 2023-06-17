package com.example.gadalka.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gadalka.Options
import com.example.gadalka.databinding.FragmentBaseBinding

class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private var options: Options? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            options = it.getParcelable(KEY_OPTIONS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_OPTIONS = "OPTIONS"

        fun newInstance(options: Options): BaseFragment {
            val fragment = BaseFragment()
            val args = Bundle().apply {
                putParcelable(KEY_OPTIONS, options)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
