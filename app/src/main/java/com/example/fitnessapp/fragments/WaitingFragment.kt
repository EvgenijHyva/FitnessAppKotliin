package com.example.fitnessapp.fragments

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.adapters.ExerciseAdapter
import com.example.fitnessapp.databinding.WaitingFragmentBinding
import com.example.fitnessapp.utils.FragmentManager
import com.example.fitnessapp.utils.TimeUtils

const val  COUNT_DOWN_TIME = 11000L // 11s -> 10s

class WaitingFragment : Fragment() {
    private lateinit var binding: WaitingFragmentBinding
    private lateinit var adapter: ExerciseAdapter
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WaitingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.max = COUNT_DOWN_TIME.toInt()
        startTimer()
    }

    private fun  startTimer() = with(binding) { // recycler view
        timer = object : CountDownTimer(COUNT_DOWN_TIME, 10) {
            override fun onTick(restTime: Long) {
                // progress bar with CountDownInterval
                tvTimer.text = TimeUtils.getTime(restTime)
                progressBar.progress = restTime.toInt()
            }

            override fun onFinish() {
                // after finnish shows exercise
                FragmentManager.setFragment(ExerciseFragment.newInstance(), activity as AppCompatActivity)
            }
        }.start()
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WaitingFragment()
    }
}