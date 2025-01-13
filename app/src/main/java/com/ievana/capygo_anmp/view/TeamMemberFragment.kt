package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentTeamMemberBinding
import com.ievana.capygo_anmp.databinding.FragmentTeamsBinding
import com.ievana.capygo_anmp.viewmodel.MemberViewModel
import com.squareup.picasso.Picasso

class TeamMemberFragment : Fragment() {
    private lateinit var binding: FragmentTeamMemberBinding
    private lateinit var viewModel: MemberViewModel
    private val teamMemberAdapter = TeamMemberAdapter(arrayListOf())
    private var teamID: String? = null
    private var image: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamMemberBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val teamName = TeamMemberFragmentArgs.fromBundle(requireArguments()).teamName
//        teamID = TeamMemberFragmentArgs.fromBundle(requireArguments()).id
//
//        image = TeamMemberFragmentArgs.fromBundle(requireArguments()).image
//        Picasso.get().load(image).into(binding.imgGame)
//
//        binding.textView.text = teamName
//        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
//        viewModel.refresh("")
//
//        binding.recViewTeamMember.layoutManager = LinearLayoutManager(context)
//        binding.recViewTeamMember.adapter = teamMemberAdapter
//
//        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.membersLD.observe(viewLifecycleOwner, Observer { teams ->
            // Mengambil tim yang sesuai dengan teamID
            val selectedTeam = teams.find { it.idMember.toString() == teamID }

//            // Jika tim yang sesuai ditemukan, update daftar anggota tim di adapter
//            selectedTeam?.let {
//                teamMemberAdapter.updateTeamMember(it.teamMember)
//            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewTeamMember.visibility = View.GONE

            } else {
                binding.recViewTeamMember.visibility = View.VISIBLE
            }
        })
    }
}