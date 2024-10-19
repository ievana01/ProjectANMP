package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
            val yearsList = it.map { achievement -> achievement.year.toString() }

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                yearsList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.yearSpinner.adapter = adapter

//            var selectedYear = binding.yearSpinner.selectedItem.toString()
//            val position = yearsList.indexOf(selectedYear)
//            binding.yearSpinner.setSelection(position)
//            Log.d("SpinnerClick", "Spinner was clicked $position")
//            if(binding.yearSpinner.onItemClickListener!= null){
//                val filteredData = achievementListAdapter.achievementList.filter { achievement ->
//                achievement.year.toString() == selectedYear}
//
//            }

        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recView.visibility = View.GONE

            } else {
                binding.recView.visibility = View.VISIBLE

            }
        })
    }

}