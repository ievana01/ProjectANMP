package com.ievana.capygo_anmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.CapygoListItemBinding
import com.ievana.capygo_anmp.model.Game
import com.squareup.picasso.Picasso

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


        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener {picasso, uri, exception -> exception.printStackTrace()}
        Picasso.get().load(gameList[position].image).into(holder.binding.imageView2)
//        if (!image.isNullOrEmpty()) {
//            Picasso.get()
//                .load(image)
//                .placeholder(R.drawable.background) // Gambar sementara
//                .error(R.drawable.background) // Gambar error
//                .into(holder.binding.imageView2)
//        } else {
//            holder.binding.imageView2.setImageResource(R.drawable.background) // Set gambar default jika tidak ada URL
//        }

        val name = gameList[position].name
        val img = gameList[position].image
        holder.binding.btnTeams.setOnClickListener {
            val action = MainFragmentDirections.actionTeamsFragment(img!!)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.btnAchivement.setOnClickListener{

            val action = MainFragmentDirections.actionAchievementFragment(name!!, img!!)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateGameList(newGame : ArrayList<Game>){
        gameList.clear()
        gameList.addAll(newGame)
        notifyDataSetChanged()
    }

}