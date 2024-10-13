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
import com.ievana.capygo_anmp.viewmodel.MemberViewModel

class WhoweareFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MemberViewModel
    private val memberListAdapter = MemberListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemberViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = memberListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.membersLD.observe(viewLifecycleOwner, Observer {
            memberListAdapter.updateMemberList(it)
        })

        viewModel.memberLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                binding.txtError?.visibility = View.VISIBLE
            }else{
                binding.txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}