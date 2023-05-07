package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.DayModel
import com.example.fitnessapp.adapters.DaysAdapter
import com.example.fitnessapp.adapters.ExerciseAdapter
import com.example.fitnessapp.databinding.ExercisesListFragmentBinding
import com.example.fitnessapp.databinding.FragmentDaysBinding
import com.example.fitnessapp.utils.MainViewModel


class ExercisesListFragment : Fragment() {
    private lateinit var binding: ExercisesListFragmentBinding
    private val model: MainViewModel by activityViewModels()
    private lateinit var adapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ExercisesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.mutableListExercise.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun  init() = with(binding) { // recycler view
        adapter = ExerciseAdapter()
        rcView.layoutManager = LinearLayoutManager(activity)
        rcView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExercisesListFragment()
    }
}