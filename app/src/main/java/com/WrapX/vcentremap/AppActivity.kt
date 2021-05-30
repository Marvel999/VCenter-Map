package com.WrapX.vcentremap

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.WrapX.vcentremap.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityAppBinding.inflate(layoutInflater)
     setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_app)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_find_vcentre, R.id.navigation_vcentre,R.id.navigation_slot))
        navView.setupWithNavController(navController)
    }
}