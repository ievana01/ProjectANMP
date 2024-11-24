package com.ievana.capygo_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.FragmentLoginBinding
import com.ievana.capygo_anmp.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var viewModel:UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnLogin.setOnClickListener{
            val uname = binding.txtInputUsername.text.toString()
            val pass = binding.txtInputPassword.text.toString()

            if (uname.isNotEmpty() && pass.isNotEmpty()){
                lifecycleScope.launch(Dispatchers.IO){
                    val user = viewModel.loginUser(uname,pass)
                    launch(Dispatchers.Main){
                        if (user != null){
                            Toast.makeText(
                                requireContext(),
                                "Welcome, ${user.firstName}!",
                                Toast.LENGTH_LONG
                            ).show()
                            val action = LoginFragmentDirections.actionHomeFragment("")
                            Navigation.findNavController(view).navigate(action)
                        } else{
                            Toast.makeText(
                                requireContext(),
                                "Invalid username or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            } else{
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            }

        }

        binding.btnSignUp.setOnClickListener{
            val action = LoginFragmentDirections.actionSignUp()
            Navigation.findNavController(it).navigate(action)
        }
    }
}