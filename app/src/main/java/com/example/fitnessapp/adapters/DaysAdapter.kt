package com.example.fitnessapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.DaysListItemBinding


class DaysAdapter(var listener: Listener) : ListAdapter<DayModel,
        DaysAdapter.DayHolder>(
            MyComparator()
        ) { // constructor compare elements
    class DayHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = DaysListItemBinding.bind(view) //days_list_item
        fun setData(data: DayModel, listener: Listener) = with(binding) {// for direct identification (not necessary)
            // get resources from binding context
            val name = root.context.getString(R.string.day) + " ${adapterPosition + 1}"
            textViewName.text = name
            val exCounter = data.exercises // array from res.values.arrays
                .split(",").size.toString() + " " +
                    root.context.getString(R.string.exercise)
            textViewCounter.text = exCounter
            // listener on element
            itemView.setOnClickListener {
                listener.onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        // crete element days_list_item view
        val view = LayoutInflater.from(
            parent.context // parent contains context
        ).inflate( // inflate resource
            R.layout.days_list_item, parent, false
        )
        return DayHolder(view) // class instance with binding
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        // fill element after onCreateViewHolder
        val day = getItem(position)
        holder.setData(day, listener) // interface listener for accessing
    }

    class MyComparator : DiffUtil.ItemCallback<DayModel>() {
        override fun areItemsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            // this is simple example
            return oldItem == newItem // compare for unic props example (id from db)
        }

        override fun areContentsTheSame(oldItem: DayModel, newItem: DayModel): Boolean {
            return oldItem == newItem // compare props
        }
    }
    interface Listener {
        // interface works as bridge
        fun onClick (day: DayModel)
    }
}