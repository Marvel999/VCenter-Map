package com.WrapX.vcentremap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.WrapX.vcentremap.repo.SharePrefrance.UserSharedPreferences

class MainActivity : AppCompatActivity() {
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController= Navigation.findNavController(findViewById(R.id.nav_host_fragment));
        val userSharedPreferences=UserSharedPreferences(this)

        //use string resource R.
        //Always relative name , Boolean isLogin
        if (userSharedPreferences.name == "Buddy")
        navController.navigate(R.id.loginFragment)
        else{
            navController.navigate(R.id.pinCodeFragment)
        }
    }
}