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
import com.ievana.capygo_anmp.databinding.AchievementItemBinding
import com.ievana.capygo_anmp.databinding.FragmentAchievementBinding
import com.ievana.capygo_anmp.viewmodel.AchievementViewModel
import com.squareup.picasso.Picasso

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
        val gameName = AchievementFragmentArgs.fromBundle(requireArguments()).name
        val img = AchievementFragmentArgs.fromBundle(requireArguments()).image
        binding.txtGameName.text = gameName
        Picasso.get().load(img).into(binding.gameImage)

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