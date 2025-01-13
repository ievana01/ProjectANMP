package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentTeamsBinding
import com.ievana.capygo_anmp.databinding.FragmentTesBinding
import com.ievana.capygo_anmp.viewmodel.MemberViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TesFragment : Fragment() {
    private lateinit var binding: FragmentTesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idGame = AchievementFragmentArgs.fromBundle(requireArguments()).idGame


        binding.txtNamaGame.text = idGame.toString()


    }

}