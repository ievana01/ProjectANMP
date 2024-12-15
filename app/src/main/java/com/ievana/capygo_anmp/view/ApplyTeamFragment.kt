package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
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
        viewModel = ViewModelProvider(this).get(ProposalViewModel::class.java)
        viewModel.refresh()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = propAdapter
        binding.swipeRefreshLayout.setOnRefreshListener {

            binding.recyclerView.visibility = View.GONE
            binding.txterror.visibility = View.GONE
            binding.progressBar2.visibility = View.VISIBLE
            viewModel.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        binding.btnNewApplyTeam.setOnClickListener{
            val action = ApplyTeamFragmentDirections.actionNewApplyTeam()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.proposalLD.observe(viewLifecycleOwner, Observer {
            val prop = ArrayList(it)
            propAdapter.updateProposalList(prop)
        })
        viewModel.proposalLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txterror?.visibility = View.VISIBLE
            } else {
                binding.txterror?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recyclerView.visibility = View.GONE
                binding.progressBar2.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBar2.visibility = View.GONE
            }
        })

    }
}