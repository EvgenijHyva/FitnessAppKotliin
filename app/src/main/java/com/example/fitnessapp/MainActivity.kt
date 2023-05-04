package com.example.fitnessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.fragments.DaysFragment
import com.example.fitnessapp.utils.FragmentManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentManager.setFragment(DaysFragment.newInstance(), this)
    }
}

