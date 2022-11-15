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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.skillbox.aslanbolurov.appquiz.QuizStorage
import com.skillbox.aslanbolurov.appquiz.R
import com.skillbox.aslanbolurov.appquiz.databinding.FragmentSurveyScreenBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SurveyScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SurveyScreenFragment : Fragment() {

    private var _binding:FragmentSurveyScreenBinding?=null
    private val binding get()=_binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentSurveyScreenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            setAlphaAnimationOnAllViews()

            findNavController().navigate(R.id.action_SurveyFragment_to_WelcomeFragment).apply {
                exitTransition=TransitionInflater.from(requireContext()).inflateTransition(R.transition.grid_transition)
            }

        }
        binding.btnToResult.setOnClickListener {
            setAlphaAnimationOnAllViews()

            val bundle=Bundle().apply {
                putInt("param1",getIndexOfAnswer(binding.radioGroupQuestion1.checkedRadioButtonId,1))
                putInt("param2",getIndexOfAnswer(binding.radioGroupQuestion2.checkedRadioButtonId,2))
                putInt("param3",getIndexOfAnswer(binding.radioGroupQuestion3.checkedRadioButtonId,3))
            }
            findNavController().navigate(
                R.id.action_SurveyFragment_to_ResultFragment
                ,args=bundle
                ).apply {
                enterTransition= Explode()
                exitTransition= Explode()
            }
        }

        //filling textViews
        binding.tvFirstQuestion.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].question)
        binding.tvSecondQuestion.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].question)
        binding.tvThirdQuestion.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].question)
        //filling radioButtons
        binding.optAnswer1Q1.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[0])
        binding.optAnswer2Q1.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[1])
        binding.optAnswer3Q1.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[2])
        binding.optAnswer4Q1.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].answers[3])
        binding.optAnswer1Q2.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[0])
        binding.optAnswer2Q2.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[1])
        binding.optAnswer3Q2.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[2])
        binding.optAnswer4Q2.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].answers[3])
        binding.optAnswer1Q3.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[0])
        binding.optAnswer2Q3.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[1])
        binding.optAnswer3Q3.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[2])
        binding.optAnswer4Q3.setText(
            QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].answers[3])




    }

    private fun setAlphaAnimationOnAllViews() {
        binding.root.animate().apply {
            alpha(0f)
            duration=1000
        }
    }


    private fun getIndexOfAnswer(id:Int, radioGroup:Int):Int {
        var resultIndex = 0
        when (radioGroup) {
            1 -> {
                when (id) {
                    binding.optAnswer1Q1.id -> resultIndex = 0
                    binding.optAnswer2Q1.id -> resultIndex = 1
                    binding.optAnswer3Q1.id -> resultIndex = 2
                    binding.optAnswer4Q1.id -> resultIndex = 3
                }
            }
            2 -> {
                when (id) {
                    binding.optAnswer1Q2.id -> resultIndex = 0
                    binding.optAnswer2Q2.id -> resultIndex = 1
                    binding.optAnswer3Q2.id -> resultIndex = 2
                    binding.optAnswer4Q2.id -> resultIndex = 3
                }
            }
            3 -> {
                when (id) {
                    binding.optAnswer1Q3.id -> resultIndex = 0
                    binding.optAnswer2Q3.id -> resultIndex = 1
                    binding.optAnswer3Q3.id -> resultIndex = 2
                    binding.optAnswer4Q3.id -> resultIndex = 3
                }
            }
        }
        return resultIndex
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null


    }
    private fun setAllViewOnAlphaAnimation(){
        val listOfViews= mutableListOf<View>()
        listOfViews+= arrayOf()


    }
}

