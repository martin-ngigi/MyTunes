package com.example.mytunes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytunes.R
import com.example.mytunes.adapters.AuthAdapter
import com.example.mytunes.databinding.FragmentAuthBinding
import com.example.mytunes.domain.FragObject

class AuthFragment : Fragment() {
    private lateinit var binding:FragmentAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater,container,false)
        val list = ArrayList<FragObject>()
        list.add(FragObject("Login",LoginFragment()))
        list.add(FragObject("Register",RegisterFragment()))
        val pagerAdapter = AuthAdapter(requireActivity().supportFragmentManager,list)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }

    companion object {
        private val TAG = AuthFragment::class.simpleName
    }
}