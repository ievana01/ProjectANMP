package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentNewApplyTeamBinding
import com.ievana.capygo_anmp.model.Proposal
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.model.User
import com.ievana.capygo_anmp.viewmodel.AchievementViewModel
import com.ievana.capygo_anmp.viewmodel.ListViewModel
import com.ievana.capygo_anmp.viewmodel.MemberViewModel
import com.ievana.capygo_anmp.viewmodel.ProposalViewModel


class NewApplyTeamFragment : Fragment() {

    private lateinit var binding: FragmentNewApplyTeamBinding
    private lateinit var gameList:List<String>
    private lateinit var teamList:List<String>
    private lateinit var viewModel: ListViewModel
    private lateinit var viewModelProp: ProposalViewModel
    private lateinit var viewModelTeam: MemberViewModel
    private var selectedGame:String=""
    private var selectedTeam:String=""

    private var isSpinnerInitialized = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewApplyTeamBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        viewModelTeam = ViewModelProvider(this).get(MemberViewModel::class.java)
        viewModelTeam.refresh("")

        viewModelProp = ViewModelProvider(this).get(ProposalViewModel::class.java)

        binding.gameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (isSpinnerInitialized) {
                    if (position >= 0) {
                        selectedGame = binding.gameSpinner.selectedItem.toString()
                        viewModel.refresh()
                        Log.d("game", "Game yang dipilih: $selectedGame")
                        viewModelTeam.refresh(selectedGame)
                    } else {
                        viewModel.refresh()
                    }
                } else {
                    isSpinnerInitialized = true
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("game", "Tidak ada game yang dipilih")
            }
        }
        
        binding.teamSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position >= 0) {
                    selectedTeam = binding.teamSpinner.selectedItem.toString()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("team", "Tidak ada team yang dipilih")
            }
        }

        binding.btnApply.setOnClickListener{
            val desc = binding.txtDescription.text.toString()

            val prop = Proposal(selectedGame, selectedTeam, desc, "Waiting")
            val list = listOf(prop)
            viewModelProp.addProp(list)
            Toast.makeText(view.context, "Proposal successfully added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }


        observeViewModel()
    }


    fun observeViewModel() {
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {

            gameList = it.map { game -> game.name.toString() }

            if(!isSpinnerInitialized){
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                    gameList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.gameSpinner.adapter = adapter
            }
        })


//        viewModelTeam.membersLD.observe(viewLifecycleOwner, Observer {
//            val filteredTeams = it.filter { team -> team.gameName == selectedGame }
//
//            teamList = filteredTeams.map { team -> team.teamName }
//
//            val teamAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
//                teamList)
//            teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.teamSpinner.adapter = teamAdapter
//        })


    }
}