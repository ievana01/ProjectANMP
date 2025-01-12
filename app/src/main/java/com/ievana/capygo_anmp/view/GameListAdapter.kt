package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.databinding.CapygoListItemBinding
import com.ievana.capygo_anmp.model.Game
import com.squareup.picasso.Picasso

class GameListAdapter(val gameList: ArrayList<Game>) : RecyclerView.Adapter<GameListAdapter.GameViewHolder>(), TeamClickListerner, AchieveClickListerner{
    class GameViewHolder(var binding:CapygoListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = CapygoListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.game = gameList[position]
        holder.binding.teamListener = this
        holder.binding.achieveListerner = this
//        holder.binding.txtEsportName.text = gameList[position].name
//        holder.binding.txtDescription.text = gameList[position].description
//
//        val builder = Picasso.Builder(holder.itemView.context)
//        builder.listener {picasso, uri, exception -> exception.printStackTrace()}
//        Picasso.get().load(gameList[position].image).into(holder.binding.imageView2)
//
//        val name = gameList[position].name
//        val img = gameList[position].image
//        holder.binding.btnTeams.setOnClickListener {
//            val action = MainFragmentDirections.actionTeamsFragment(img!!)
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.binding.btnAchivement.setOnClickListener{
//
//            val action = MainFragmentDirections.actionAchievementFragment(name!!, img!!)
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun updateGameList(newGame: List<Game>){
        gameList.clear()
        gameList.addAll(newGame)
        notifyDataSetChanged()
    }

    override fun teamClick(v: View) {
        val image = v.tag.toString()
        val action = MainFragmentDirections.actionTeamsFragment(image)
        Navigation.findNavController(v).navigate(action)
    }

    override fun achieveClick(v: View) {
        val name = v.tag.toString()
        val action = MainFragmentDirections.actionAchievementFragment(name)
        Navigation.findNavController(v).navigate(action)
    }

}