package com.example.fitnessapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.DayModel
import com.example.fitnessapp.adapters.DaysAdapter
import com.example.fitnessapp.databinding.FragmentDaysBinding
import com.example.fitnessapp.utils.FragmentManager


class DaysFragment : Fragment(), DaysAdapter.Listener {
    private lateinit var binding: FragmentDaysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRecyclerView() = with(binding) {
        val adapter = DaysAdapter(this@DaysFragment) // expect listener from correct fragment
        rcViewDays.layoutManager = LinearLayoutManager(activity as AppCompatActivity)
        rcViewDays.adapter = adapter
        adapter.submitList(fillDaysArray())
    }

    private fun fillDaysArray() : ArrayList<DayModel> {
        val tempArray = ArrayList<DayModel> () // init class array
        resources.getStringArray(R.array.day_exercises).forEach { // method from resources only
            tempArray.add(
                DayModel(it, false)
            )
        }
        return tempArray
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onClick(day: DayModel) {
        FragmentManager.setFragment(
            ExercisesListFragment.newInstance(), activity as AppCompatActivity
        )
    }
}