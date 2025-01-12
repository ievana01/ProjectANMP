package com.ievana.capygo_anmp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ievana.capygo_anmp.R
import com.ievana.capygo_anmp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //navcontroller diambil dari navhostfragment
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController

        //drawer
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        binding.bottomNav.menu.findItem(R.id.itemSignOut)?.isVisible = false

        binding.bottomNav.setupWithNavController(navController)
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.itemSignOut -> {
                    // Handle Profile item
                    logout()
                    true
                }
                else -> false
            }
        }

    }

    private fun logout() {

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("username")
        editor.apply()

        val action = MainFragmentDirections.actionloginFalse()
        navController.navigate(action)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp()
    }

}