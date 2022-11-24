package com.example.mytunes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.navigation.fragment.NavHostFragment
import com.example.mytunes.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        if (FirebaseAuth.getInstance().uid == null) {
            Handler().postDelayed({
                navController.navigate(R.id.action_splashFragment_to_authFragment)
            }, 3000)
        } else {
            Handler().postDelayed({
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            }, 3000)
        }
    }
}