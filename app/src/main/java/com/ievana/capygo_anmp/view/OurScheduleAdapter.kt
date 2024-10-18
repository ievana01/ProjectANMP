package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.CapygoListItemBinding
import com.ievana.capygo_anmp.databinding.ScheduleListItemBinding
import com.ievana.capygo_anmp.model.Schedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OurScheduleAdapter(val scheduleList: ArrayList<Schedule>) : RecyclerView.Adapter<OurScheduleAdapter.OurScheduleViewHolder>(){
    class  OurScheduleViewHolder(var binding: ScheduleListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurScheduleViewHolder {
        val binding = ScheduleListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OurScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }

    override fun onBindViewHolder(holder: OurScheduleViewHolder, position: Int) {
        val dateString = scheduleList[position].date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date? = dateFormat.parse(dateString)

        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())

        val dayString = dayFormat.format(date ?: Date())
        val monthString = monthFormat.format(date ?: Date()).uppercase()
        holder.binding.txtDate.text = dayString
        holder.binding.txtMonth.text = monthString
        holder.binding.txtYT.text = scheduleList[position].ytLS
        holder.binding.txtTeam.text = scheduleList[position].gameName+" - "+scheduleList[position].team

        holder.binding.linearLayoutSchedule.setOnClickListener{
            val action = OurScheduleFragmentDirections.actionScheduleDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateScheduleList(newSchedule : ArrayList<Schedule>){
        scheduleList.clear()
        scheduleList.addAll(newSchedule)
        notifyDataSetChanged()
    }
}