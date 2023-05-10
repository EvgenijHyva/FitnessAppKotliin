package com.example.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ExerciseModel
import com.example.fitnessapp.databinding.ExerciseBinding
import com.example.fitnessapp.utils.MainViewModel
import com.example.fitnessapp.utils.TimeUtils
import pl.droidsonroids.gif.GifDrawable


class ExerciseFragment : Fragment() {
    private lateinit var binding: ExerciseBinding
    private val model: MainViewModel by activityViewModels()
    private var exerciseCounter = 0
    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var timer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.mutableListExercise.observe(viewLifecycleOwner) {
            exerciseList = it
            nextExercise()
        }
        binding.buttonNextExercise.setOnClickListener{
            nextExercise()
        }
    }

    private fun nextExercise () {
        if (exerciseCounter < exerciseList?.size!!) {
            val exerciseItem = exerciseList?.get(exerciseCounter++) ?: return
            showExercise(exerciseItem)
            setExerciseType(exerciseItem)
            showUpcomingExercise()
        } else {
            Toast.makeText(activity, "All done", Toast.LENGTH_LONG).show()
        }
    }

    private fun showExercise(exercise: ExerciseModel) = with(binding) {
        imMain.setImageDrawable(GifDrawable(root.context.assets, exercise.imagePath))
        tvExerciseName.text = exercise.exerciseName
    }

    private fun setExerciseType (exercise: ExerciseModel) = with(binding) {
        if(exercise.timeOrRepeats.startsWith("x")){
            tvExerciseTime.text = exercise.timeOrRepeats
        } else {
            startTimer(exercise)
        }
    }

    private fun startTimer(exercise: ExerciseModel) = with(binding) {
        timer?.cancel()
        val time = exercise.timeOrRepeats.toInt() * 1000
        progressBar3.max = time
        timer = object : CountDownTimer(time.toLong() , 10) {
            override fun onTick(restTime: Long) {
                tvExerciseTime.text = TimeUtils.getTime(restTime)
                progressBar3.progress = restTime.toInt()
            }
            override fun onFinish() {
                nextExercise()
            }
        }.start()
    }

    private fun showUpcomingExercise() = with(binding) {
        if (exerciseCounter < exerciseList?.size!!) {
            val exerciseItem = exerciseList?.get(exerciseCounter) ?: return
            tvNextExerciseName.text = exerciseItem.exerciseName
            imNext.setImageDrawable(GifDrawable(root.context.assets, exerciseItem.imagePath))
        } else {
            tvNextExerciseName.text = getString(R.string.finnish_exercise)
            val congratulationsArray = resources.getStringArray(R.array.congratulations)
            val randomCongratsPath = congratulationsArray.random()
            imNext.setImageDrawable(GifDrawable(root.context.assets, randomCongratsPath.toString()))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExerciseFragment()
    }

    override fun onDetach() {
        timer?.cancel()
        super.onDetach()
    }
}