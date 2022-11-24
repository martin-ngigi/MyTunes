package com.example.mytunes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytunes.R
import com.example.mytunes.databinding.FragmentSignUpBinding

class RegisterFragment: Fragment() {

    lateinit var binding: FragmentSignUpBinding

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

        //get data from UI
        var first_name = binding.fName.editableText.toString()
        var last_name = binding.lName.editableText.toString()
        var email = binding.email.editableText.toString()
        var phone = binding.phone.editableText.toString()
        var password = binding.registerPassword.editableText.toString()

    }


}