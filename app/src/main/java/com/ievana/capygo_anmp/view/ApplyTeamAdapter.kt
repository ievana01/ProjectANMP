package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.ProposalListItemBinding
import com.ievana.capygo_anmp.model.Proposal
import java.util.ArrayList

class ApplyTeamAdapter(val propList: ArrayList<Proposal>)
    : RecyclerView.Adapter<ApplyTeamAdapter.ProposalViewHolder>() {

    class ProposalViewHolder(var binding: ProposalListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProposalViewHolder {
        val binding = ProposalListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProposalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProposalViewHolder, position: Int) {
        holder.binding.proposal = propList[position]
    }

    override fun getItemCount(): Int {
        return propList.size
    }

    fun updateProposalList(newProposalList: ArrayList<Proposal>) {
        propList.clear()
        propList.addAll(newProposalList)
        notifyDataSetChanged()
    }
}
