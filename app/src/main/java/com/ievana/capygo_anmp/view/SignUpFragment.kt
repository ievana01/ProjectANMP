package com.ievana.capygo_anmp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentSignUpBinding
import com.ievana.capygo_anmp.model.User
import com.ievana.capygo_anmp.viewmodel.UserViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding:FragmentSignUpBinding
    private lateinit var viewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnSubmit.setOnClickListener {
            if(binding.inputPassword.text.toString() == binding.inputRepPass.text.toString() && binding.checkBoxAgree.isChecked) {
                var user = User(
                    binding.inputFirstName.text.toString(),
                    binding.inputLastName.text.toString(),
                    binding.inputUsername.text.toString(),
                    binding.inputRepPass.text.toString(),
                )
                val list = listOf(user)
                viewModel.addUser(list)
                Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()
            }
            else{
                Toast.makeText(view.context, "Cannot Add Data", Toast.LENGTH_LONG).show()
            }

        }
    }
}