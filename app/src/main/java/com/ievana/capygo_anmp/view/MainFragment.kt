package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ievana.capygo_anmp.databinding.FragmentMainBinding
import com.ievana.capygo_anmp.model.Game
import com.ievana.capygo_anmp.viewmodel.ListViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: ListViewModel
    private val gameListAdapter = GameListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = gameListAdapter
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.gamesLD.observe(viewLifecycleOwner, Observer {
            gameListAdapter.updateGameList(it)
        })

        viewModel.gameLoadErrorLD.observe(viewLifecycleOwner, Observer {
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