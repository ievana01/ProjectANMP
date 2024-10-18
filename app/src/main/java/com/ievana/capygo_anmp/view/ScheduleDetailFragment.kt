package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentScheduleDetailBinding
import com.squareup.picasso.Picasso

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
            val image = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).gameImage
            val yt = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).ytName
            val location = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).location
            val time = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).time
            val tim = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).team
            val desc = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).desc
            val name = ScheduleDetailFragmentArgs.fromBundle(requireArguments()).gameName
            Picasso.get().load(image).into(binding.imgGameSch)
            binding.txtYt.text = yt +" - "+name
            binding.txtLocation.text = location+" ("+time+")"
            binding.txtTeamSch.text = tim
            binding.txtDescSch.text = desc


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