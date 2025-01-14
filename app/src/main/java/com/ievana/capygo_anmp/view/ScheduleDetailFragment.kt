package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentScheduleDetailBinding
import com.ievana.capygo_anmp.model.GameDao
import com.ievana.capygo_anmp.model.GameDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScheduleDetailFragment : Fragment() {
    private lateinit var binding : FragmentScheduleDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentScheduleDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val idSchedule = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).idSchedule
            val database = GameDatabase.invoke(requireContext())
            val dao = database.gameDao()

            CoroutineScope(Dispatchers.IO).launch {
                val schedule = dao.getScheduleById(idSchedule)
                val game = dao.getGameById(schedule.idGame)

                // Update UI di thread utama
                withContext(Dispatchers.Main) {
                    binding.schedule = schedule
                    binding.game = game
                }
            }

        }

        binding.btnNotif.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage("Notification created.")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            val alert = dialogBuilder.create()
            alert.setTitle("Notification")
            alert.show()
        }
    }


}