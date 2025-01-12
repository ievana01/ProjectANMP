package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.ievana.capygo_anmp.databinding.TeamsListItemBinding
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.viewmodel.MemberViewModel

class TeamAdapter(val teamList: ArrayList<Team>,private val img: String?) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    class TeamViewHolder(var binding: TeamsListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.binding.txtTeamName.text = teamList[position].teamName
        holder.binding.cardView.setOnClickListener {
            val teamName = teamList[position].teamName
            val id = teamList[position].idTeam
            val action = TeamsFragmentDirections.actionTeamMember(teamName!!,id.toString()!!,img!!)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateTeam(newTeam : ArrayList<Team>){
        teamList.clear()
        teamList.addAll(newTeam)
        notifyDataSetChanged()
    }

}