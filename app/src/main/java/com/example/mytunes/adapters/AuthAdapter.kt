package com.example.mytunes.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mytunes.domain.FragObject

class AuthAdapter(fm:FragmentManager,private val list: List<FragObject>):FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment =list[position].fragment

    override fun getPageTitle(position: Int): CharSequence =list[position].title
}