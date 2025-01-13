package com.ievana.capygo_anmp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.TeamDetailItemBinding

import com.ievana.capygo_anmp.databinding.TeamsListItemBinding
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.model.Team
import com.squareup.picasso.Picasso

class TeamMemberAdapter(val teamMemberList: ArrayList<Member>) : RecyclerView.Adapter<TeamMemberAdapter.TeamMemberViewHolder>() {
    class TeamMemberViewHolder(var binding: TeamDetailItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberAdapter.TeamMemberViewHolder {
        val binding = TeamDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        holder.binding.member = teamMemberList[position]
//        holder.binding.team = teamMemberList[position]
//        holder.binding.txtRole.text = teamMemberList[position].role
//        holder.binding.txtMemberName.text = teamMemberList[position].memberName
//        val builder = Picasso.Builder(holder.itemView.context)
//        builder.listener {picasso, uri, exception -> exception.printStackTrace()}
//        Picasso.get().load(teamMemberList[position].memberImage).into(holder.binding.imageView3)
    }

    override fun getItemCount(): Int {
        return teamMemberList.size
    }

    fun updateTeamMember(newTeamMember : ArrayList<Member>){
        teamMemberList.clear()
        teamMemberList.addAll(newTeamMember)
        notifyDataSetChanged()
    }

}