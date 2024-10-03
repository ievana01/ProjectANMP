package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.MemberListItemBinding
import com.ievana.capygo_anmp.model.Member

class MemberListAdapter(val memberList:ArrayList<Member>):RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>(){
    class MemberViewHolder(var binding : MemberListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = MemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.binding.txtName.text = memberList[position].name
        holder.binding.txtDesc.text = memberList[position].desc
        holder.binding.btnLike.text = memberList[position].like.toString()

        holder.binding.btnLike.setOnClickListener {
            memberList[position].like += 1
            holder.binding.btnLike.text = memberList[position].like.toString()
            notifyItemChanged(position)
        }
    }

    fun updateMemberList(newMember : ArrayList<Member>){
        memberList.clear()
        memberList.addAll(newMember)
        notifyDataSetChanged()
    }
}