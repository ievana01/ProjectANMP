package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.AchievementItemBinding
import com.ievana.capygo_anmp.model.Achievement

class AchievementAdapter(val achievementList: ArrayList<Achievement>):RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {
    class AchievementViewHolder(var binding : AchievementItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AchievementAdapter.AchievementViewHolder {
        val binding = AchievementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AchievementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementAdapter.AchievementViewHolder, position: Int) {
        holder.binding.txtNumber.text = (position+1).toString()
        holder.binding.txtACName.text = achievementList[position].winningTeam
        holder.binding.txtACAchiv.text = achievementList[position].competitionTitle + " ("+achievementList[position].year+") -"
    }

    override fun getItemCount(): Int {
        return achievementList.size
    }

    fun updateAchievement(newAchievement: ArrayList<Achievement>){
        achievementList.clear()
        achievementList.addAll(newAchievement)
        notifyDataSetChanged()
    }


}