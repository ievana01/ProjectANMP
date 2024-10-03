package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.CapygoListItemBinding
import com.ievana.capygo_anmp.model.Game

class GameListAdapter(val gameList: ArrayList<Game>) : RecyclerView.Adapter<GameListAdapter.GameViewHolder>(){
    class GameViewHolder(var binding:CapygoListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = CapygoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.txtEsportName.text = gameList[position].name
        holder.binding.txtDescription.text = gameList[position].description

//        holder.binding.btnTeams.setOnClickListener {
//
//        }
    }

    fun updateGameList(newGame : ArrayList<Game>){
        gameList.clear()
        gameList.addAll(newGame)
        notifyDataSetChanged()
    }

}