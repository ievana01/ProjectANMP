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

import com.ievana.capygo_anmp.databinding.FragmentTeamsBinding

import com.ievana.capygo_anmp.viewmodel.MemberViewModel
import com.squareup.picasso.Picasso
class TeamsFragment : Fragment() {
    private lateinit var binding: FragmentTeamsBinding
    private lateinit var viewModel: MemberViewModel
    private var memberListAdapter = TeamAdapter(arrayListOf(), "")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idGame = TeamsFragmentArgs.fromBundle(requireArguments()).idGame

        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
        viewModel.refresh(idGame)
        viewModel.fetchImg(idGame)

        binding.recViewTeams.layoutManager = LinearLayoutManager(context)
        binding.recViewTeams.adapter = memberListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.membersLD.observe(viewLifecycleOwner, Observer {
            memberListAdapter.updateTeam(it)
        })

        viewModel.imgLD.observe(viewLifecycleOwner, Observer { imageUrl ->

            val imgURLClean = imageUrl[0].replace("[", "").replace("]", "")
            if (!imageUrl.isNullOrEmpty()) {
                Picasso.get()
                    .load(imgURLClean)
                    .into(binding.imgTeam)
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewTeams.visibility = View.GONE

            } else {
                binding.recViewTeams.visibility = View.VISIBLE
            }
        })
    }
}