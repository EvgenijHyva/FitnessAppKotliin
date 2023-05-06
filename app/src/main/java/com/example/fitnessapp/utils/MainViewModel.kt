package com.example.fitnessapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.adapters.ExerciseModel

class MainViewModel : ViewModel() {
    // ViewModel -> does not destroy after activity destroy.
    // Example: changing screen orientation -> fragment recreation
    val mutableListExercise = MutableLiveData<ArrayList<ExerciseModel>>()
    // after, connect to activity and fragments
}