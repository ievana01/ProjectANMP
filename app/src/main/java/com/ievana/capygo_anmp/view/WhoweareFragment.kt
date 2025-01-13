package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentMainBinding
import com.ievana.capygo_anmp.databinding.FragmentWhoweareBinding
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.viewmodel.MemberViewModel

class WhoweareFragment : Fragment() , LikeTeamClickListener{

    private lateinit var binding: FragmentWhoweareBinding
    private lateinit var viewModel: MemberViewModel
    private val memberListAdapter = MemberListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhoweareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
//        viewModel.refresh("")
        viewModel.fetchTeam()
        binding.recViewWhoWeAre.layoutManager = LinearLayoutManager(context)
        binding.recViewWhoWeAre.adapter = memberListAdapter

        binding.refreshLayout.setOnRefreshListener {
            binding.recViewWhoWeAre.visibility = View.GONE
            binding.txtErrorWhoWeAre.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            viewModel.refresh("")
            binding.refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.membersLD.observe(viewLifecycleOwner, Observer {
            memberListAdapter.updateMemberList(it)
        })

        viewModel.memberLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtErrorWhoWeAre?.visibility = View.VISIBLE
            }else{
                binding.txtErrorWhoWeAre?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewWhoWeAre.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.recViewWhoWeAre.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun likeTeamClick(team: Team) {
        val updateLike = team.like + 1
        viewModel.updateTeamLike(updateLike, team.idTeam)
        team.like = updateLike
        memberListAdapter.notifyDataSetChanged()
    }
}