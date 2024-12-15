package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentApplyTeamBinding
import com.ievana.capygo_anmp.viewmodel.ProposalViewModel

class ApplyTeamFragment : Fragment() {
    private lateinit var binding:FragmentApplyTeamBinding
    private lateinit var viewModel: ProposalViewModel
    private val propAdapter = ApplyTeamAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentApplyTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewApplyTeam.setOnClickListener{
            val action = ApplyTeamFragmentDirections.actionNewApplyTeam()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {

    }
}