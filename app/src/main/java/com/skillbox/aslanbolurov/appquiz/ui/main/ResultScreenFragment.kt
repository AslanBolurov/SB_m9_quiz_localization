package com.skillbox.aslanbolurov.appquiz.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.Explode
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import com.skillbox.aslanbolurov.appquiz.QuizStorage
import com.skillbox.aslanbolurov.appquiz.R
import com.skillbox.aslanbolurov.appquiz.databinding.FragmentResultScreenBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultScreenFragment : Fragment() {

    private var _binding: FragmentResultScreenBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: Int = 0
    private var param2: Int = 0
    private var param3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultScreenBinding.inflate(inflater)
        binding.tvResult.setText(getResultText(param1, param2, param3))
        binding.btnStartAgain.setOnClickListener {
            findNavController().navigate(R.id.action_ResultFragment_to_SurveyFragment).apply {
                enterTransition = Slide(Gravity.RIGHT)
                exitTransition = Slide(Gravity.RIGHT)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ObjectAnimator.ofFloat(binding.btnStartAgain,View.ROTATION,0f,360f).apply {
            duration=5000
            interpolator=AccelerateDecelerateInterpolator()
            repeatCount=ObjectAnimator.INFINITE
            repeatMode=ObjectAnimator.REVERSE
            start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

private fun getResultText(
    param1: Int?, param2: Int?, param3: Int?
): String {
    return "THE RESULTS OF SURVEY :\n " +
            "The first question:${QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].question}\n" +
            "Answer: ${QuizStorage.getQuiz(QuizStorage.Locale.En).questions[0].feedback[param1!!]}\n" +
            "The second question:${QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].question}\n" +
            "Answer: ${QuizStorage.getQuiz(QuizStorage.Locale.En).questions[1].feedback[param2!!]}\n" +
            "The third question:${QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].question}\n" +
            "Answer: ${QuizStorage.getQuiz(QuizStorage.Locale.En).questions[2].feedback[param3!!]}"

}