package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DaysListItemBinding
import com.example.fitnessapp.databinding.ExerciseListItemBinding
import com.example.fitnessapp.databinding.ExercisesListFragmentBinding
import pl.droidsonroids.gif.GifDrawable


class ExerciseAdapter() : ListAdapter<ExerciseModel,
        ExerciseAdapter.ExerciseHolder>(
            MyComparator()
        ) { // constructor compare elements
    class ExerciseHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ExerciseListItemBinding.bind(view) // exercises_list_item
        fun setData(data: ExerciseModel) = with(binding) {// for direct identification (not necessary)
            tvExerciseName.text = data.exerciseName
            tvCount.text= data.timeOrRepeats
            imExcersize.setImageDrawable(GifDrawable(root.context.assets, data.imagePath))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        // crete element days_list_item view
        val view = LayoutInflater.from(
            parent.context // parent contains context
        ).inflate( // inflate resource
            R.layout.exercise_list_item, parent, false
        )
        return ExerciseHolder(view) // class instance with binding
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        // fill element after onCreateViewHolder
        val exercise = getItem(position)
        holder.setData(exercise)
    }

    class MyComparator : DiffUtil.ItemCallback<ExerciseModel>() {
        override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            // this is simple example
            return oldItem == newItem // compare for unique props example (id from db)
        }

        override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
            return oldItem == newItem // compare props
        }
    }
}