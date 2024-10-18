package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.AchievementItemBinding
import com.ievana.capygo_anmp.databinding.FragmentAchievementBinding
import com.ievana.capygo_anmp.model.Achievement
import com.ievana.capygo_anmp.viewmodel.AchievementViewModel

class AchievementFragment : Fragment() {
    private lateinit var binding: FragmentAchievementBinding
    private lateinit var viewModel: AchievementViewModel
    private val achievementListAdapter = AchievementAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, achievementListAdapter.achievementList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.yearSpinner.adapter = adapter

        val gameName = arguments?.getString("name") // Nama game yang dikirim dari Home

        Log.d("AchievementFragment", "Number of achievements: ${achievementListAdapter.achievementList
        }")
        viewModel = ViewModelProvider(this).get(AchievementViewModel::class.java)

        viewModel.refresh(gameName ?: "")

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = achievementListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.achievesLD.observe(viewLifecycleOwner, Observer {
            achievementListAdapter.updateAchievement(it)
        })

        viewModel.achievesLoadErrorLD.observe(viewLifecycleOwner, Observer {
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