package com.nirajtiwari.covid19tracker.android.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nirajtiwari.covid19tracker.Model.Tracking
import com.nirajtiwari.covid19tracker.android.databinding.StateCovidTrackingBinding

class CovidTrackerAdapters(
    private val list: List<Tracking>
) : RecyclerView.Adapter<CovidTrackerAdapters.CovidTrackerViewHolder>() {

    private lateinit var binding: StateCovidTrackingBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidTrackerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = StateCovidTrackingBinding.inflate(inflater, parent, false)
        return CovidTrackerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CovidTrackerViewHolder, position: Int) {
        val item: Tracking = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class CovidTrackerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Tracking) {
            binding.apply {
                state.text = item.state
                totalTrackings.text = "${item.total}"
                deaths.text = "${item.death}"
                hospitalizedPeople.text = "${item.hospitalized ?: 0}"
            }

        }
    }
}