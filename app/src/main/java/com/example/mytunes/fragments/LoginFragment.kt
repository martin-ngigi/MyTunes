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
import androidx.navigation.fragment.findNavController
import com.example.mytunes.R
import com.example.mytunes.databinding.FragmentLoginBinding
import com.example.mytunes.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            binding.progressBar2.visibility = VISIBLE
            binding.loginButton.isEnabled = false
            val email = binding.emailEt.editableText.toString()
            val password = binding.loginPassword.editableText.toString()
            if (email.isBlank() || password.isBlank()) {
                Snackbar.make(binding.root, "Kindly fill all the fields", Snackbar.LENGTH_LONG)
                    .apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        show()
                    }
                binding.progressBar2.visibility = INVISIBLE
                binding.loginButton.isEnabled = true
            }
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEt.error = "Invalid email address"
                binding.progressBar2.visibility = INVISIBLE
                binding.loginButton.isEnabled = true
            }
            val status = viewModel.login(email, password)
            if (status) {
                findNavController().navigate(R.id.action_authFragment_to_homeFragment)
            } else {
                Snackbar.make(binding.root, "Login failed", Snackbar.LENGTH_LONG)
                    .apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        show()
                    }
                binding.progressBar2.visibility = INVISIBLE
                binding.loginButton.isEnabled = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
}