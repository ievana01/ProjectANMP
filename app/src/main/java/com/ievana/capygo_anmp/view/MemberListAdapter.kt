package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.MemberListItemBinding
import com.ievana.capygo_anmp.model.Team
import com.squareup.picasso.Picasso

class MemberListAdapter(val memberList:ArrayList<Team>):RecyclerView.Adapter<MemberListAdapter.MemberViewHolder>(){
    class MemberViewHolder(var binding : MemberListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val binding = MemberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.binding.txtName.text = memberList[position].teamName
        holder.binding.txtDesc.text = memberList[position].desc
        holder.binding.btnLike.text = memberList[position].like.toString()

        holder.binding.btnLike.setOnClickListener {
//            memberList[position].like += 1
//            holder.binding.btnLike.text = memberList[position].like.toString()
//            notifyItemChanged(position)

            if(memberList[position].like < 20) {
                memberList[position].like += 1
                holder.binding.btnLike.text = memberList[position].like.toString()
                notifyItemChanged(position)
            }

            if(memberList[position].like == 20){
                holder.binding.btnLike.isEnabled = false
            }
        }

        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }
        Picasso.get().load(memberList[position].imageTeam).into(holder.binding.imgMember)
    }

    fun updateMemberList(newMember : ArrayList<Team>){
        memberList.clear()
        memberList.addAll(newMember)
        notifyDataSetChanged()
    }
}