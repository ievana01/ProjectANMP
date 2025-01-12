package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.ievana.capygo_anmp.databinding.FragmentAchievementBinding
import com.ievana.capygo_anmp.viewmodel.AchievementViewModel
import com.squareup.picasso.Picasso

class AchievementFragment : Fragment() {
    private lateinit var binding: FragmentAchievementBinding
    private lateinit var viewModel: AchievementViewModel
    private var achievementListAdapter = AchievementAdapter(arrayListOf() )
    private var selectedYear:String=""
    private lateinit var yearsList:List<String>
    private var gameName:String=""
    private var isSpinnerInitialized = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameName = AchievementFragmentArgs.fromBundle(requireArguments()).name
//        val img = AchievementFragmentArgs.fromBundle(requireArguments()).image
        binding.txtGameName.text = gameName
//        Picasso.get().load(img).into(binding.gameImage)

//        viewModel = ViewModelProvider(this).get(AchievementViewModel::class.java)
//        viewModel.refresh(gameName ?: "", "")
//
//        binding.yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//                if (isSpinnerInitialized) {
//                    if (position >= 0) {
//                        selectedYear = binding.yearSpinner.selectedItem.toString()
//                        viewModel.refresh(gameName, selectedYear)
//                    } else {
//                        viewModel.refresh(gameName, "")
//                    }
//                } else {
//                    isSpinnerInitialized = true
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//
//                Log.d("achievement", "Tidak ada tahun yang dipilih")
//            }
//        }
//        binding.recView.layoutManager = LinearLayoutManager(context)
//        binding.recView.adapter = achievementListAdapter
//
//        observeViewModel()
    }

    fun observeViewModel() {
//        viewModel.achievesLD.observe(viewLifecycleOwner, Observer {
//
//            yearsList = it.map { achievement -> achievement.year.toString() }
//
//            if(!isSpinnerInitialized){
//                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
//                    yearsList)
//                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                binding.yearSpinner.adapter = adapter
//            }
//
//            achievementListAdapter.updateAchievement(it)
//        })
//
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if(it == true) {
//                binding.recView.visibility = View.GONE
//            } else {
//                binding.recView.visibility = View.VISIBLE
//            }
//        })
    }

}