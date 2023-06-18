package com.example.gadalka.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gadalka.Options
import com.example.gadalka.contract.navigator
import com.example.gadalka.databinding.FragmentBaseBinding
import com.example.gadalka.model.source.Country
import com.example.gadalka.model.source.Nationality

class PersonFragment : Fragment() {

    private lateinit var binding: FragmentBaseBinding

    private  var options: Options? = null

    private var personViewModel: PersonViewModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            options = it.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT
        }
        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        binding.exit.setOnClickListener { onExitPressed() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel?.fetchPersonData(options!!.name )

//        personViewModel?.fetchNationalityData(options!!.name)
//        var temp : Nationality
//        var tempC : List<Country>
//        var t : String
//        personViewModel?.nationalityLiveData?.observe(viewLifecycleOwner) {
//            temp = it
//            tempC = temp.country
//            t = tempC[0].toString()
//        }


        personViewModel?.personLiveData?.observe(viewLifecycleOwner) { person ->
            with(binding) {
                fragmentBaseAge.text = person.age.toString()
                fragmentBaseName.text = person.name
                fragmentBaseCountry.text = person.country
                fragmentBaseGender.text = person.gender
            }

        }
    }

    private fun onExitPressed() {
        navigator().goBack()
    }



    companion object {
        private const val KEY_OPTIONS = "OPTIONS"

        fun newInstance(options: Options): PersonFragment {
            val fragment = PersonFragment()
            val args = Bundle().apply {
                putParcelable(KEY_OPTIONS, options)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
