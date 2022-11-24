package com.example.mytunes.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytunes.R
import com.example.mytunes.databinding.FragmentSignUpBinding
import com.example.mytunes.domain.User
import com.example.mytunes.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar

class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)
        binding.registerButton.setOnClickListener{
            binding.registerButton.isEnabled = false
            binding.progressBar.visibility = VISIBLE
            val firstName = binding.fName.editableText.toString()
            val lastName = binding.lName.editableText.toString()
            val email = binding.email.editableText.toString()
            val phone = binding.phone.editableText.toString()
            val password = binding.registerPassword.editableText.toString()
            if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || phone.isBlank() || password.isBlank()){
                Snackbar.make(binding.root,"Kindly fill all the fields",Snackbar.LENGTH_LONG)
                    .apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        show()
                    }
                binding.progressBar.visibility = INVISIBLE
                binding.registerButton.isEnabled = true
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.email.error = "Invalid email address"
                binding.progressBar.visibility = INVISIBLE
                binding.registerButton.isEnabled = true
                return@setOnClickListener
            }
            val user = User(null,firstName, lastName, email, phone)
            val status = viewModel.register(user, password)
            if (status){
                Snackbar.make(binding.root,"Registration successful\nLogin",Snackbar.LENGTH_LONG)
                    .apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        show()
                    }
                binding.progressBar.visibility = INVISIBLE
                binding.registerButton.isEnabled = true
            }else{
                Snackbar.make(binding.root,"Registration unsuccessful",Snackbar.LENGTH_LONG)
                    .apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        show()
                    }
                binding.progressBar.visibility = INVISIBLE
                binding.registerButton.isEnabled = true
            }
        }
    }


}