package com.example.fitnessapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnessapp.R

object FragmentManager { // not initialize
    fun setFragment(newFragment: Fragment, act_context: AppCompatActivity) {
        // need fragment and context (act_context)
        val transaction = act_context.supportFragmentManager.beginTransaction()
        // replacing fragments
        transaction.replace(R.id.placeHolder, newFragment)
        transaction.commit()
    }

}