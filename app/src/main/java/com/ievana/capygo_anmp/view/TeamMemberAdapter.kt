package com.ievana.capygo_anmp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.TeamDetailItemBinding

import com.ievana.capygo_anmp.databinding.TeamsListItemBinding
import com.ievana.capygo_anmp.model.Team

class TeamMemberAdapter(val teamMemberList: ArrayList<Team>) : RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder>() {
    class TeamMemberViewHolder(var binding: TeamDetailItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberAdapter.TeamMemberViewHolder {
        val binding = TeamDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
//        holder.binding.txtRole.text = teamMemberList[position].teamMember
    }

    override fun getItemCount(): Int {
        return teamMemberList.size
    }

    fun updateTeamMember(newTeamMember : ArrayList<Team>){
        teamMemberList.clear()
        teamMemberList.addAll(newTeamMember)
        notifyDataSetChanged()
    }

}