package com.skillbox.aslanbolurov.appquiz.ui.main

import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.transition.TransitionInflater
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.skillbox.aslanbolurov.appquiz.R
import com.skillbox.aslanbolurov.appquiz.R.layout.fragment_welcome_screen
import com.skillbox.aslanbolurov.appquiz.databinding.FragmentWelcomeScreenBinding
import java.text.SimpleDateFormat
import java.util.*

class WelcomeScreenFragment : Fragment() {

    private var _binding : FragmentWelcomeScreenBinding?=null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeScreenBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnActionToSurveyFragment.setOnClickListener{
            findNavController().navigate(R.id.action_WelcomeFragment_to_SurveyFragment).apply {
                enterTransition=Slide(Gravity.RIGHT)
                exitTransition=Slide(Gravity.LEFT)
            }
        }
        binding.btnSelectDate.setOnClickListener {

            val dateDialog=MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.choose_the_date_dialog_title))
                .setSelection(Date().time)
                .build()
            dateDialog.addOnPositiveButtonClickListener {timeInMillis ->

                val dateFormat=SimpleDateFormat("dd.MM.yyyy")

                val calendar=Calendar.getInstance()
                calendar.timeInMillis=timeInMillis

                calendar.add(Calendar.DAY_OF_MONTH,1)


                val formatter=dateFormat.format(calendar.timeInMillis)

                Snackbar.make(binding.btnSelectDate,formatter,Snackbar.LENGTH_LONG).show()
            }

            dateDialog
                .show(requireActivity().supportFragmentManager,"DatePicker")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

//    companion object {
//        fun newInstance() = WelcomeScreenFragment()
//    }
}