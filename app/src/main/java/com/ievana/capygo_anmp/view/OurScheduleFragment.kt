package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentOurScheduleBinding
import com.ievana.capygo_anmp.viewmodel.ScheduleViewModel

class OurScheduleFragment : Fragment() {
    private lateinit var binding: FragmentOurScheduleBinding
    private lateinit var viewModel: ScheduleViewModel
    private  val scheduleAdapter = OurScheduleAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOurScheduleBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        binding.recycleViewSchedule.layoutManager = LinearLayoutManager(context)
        binding.recycleViewSchedule.adapter = scheduleAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.scheduleLD.observe(viewLifecycleOwner,{
            scheduleAdapter.updateScheduleList(it)
        })

        viewModel.scheduleLoadErrorLD.observe(viewLifecycleOwner,{
            if(it == true){
                binding.recycleViewSchedule.visibility = View.GONE
            } else{
                binding.recycleViewSchedule.visibility = View.VISIBLE
            }
        })
    }


}