package org.sopt.androidassignment1.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import org.sopt.androidassignment1.ui.adapter.TabViewPagerAdapter
import org.sopt.androidassignment1.ui.view.ViewPagerFollowerFragment
import org.sopt.androidassignment1.ui.view.ViewPagerFollowingFragment
import org.sopt.androidassignment1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var tabViewPagerAdapter : TabViewPagerAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter(){
        val fragmentList = listOf(ViewPagerFollowingFragment(), ViewPagerFollowerFragment())

        tabViewPagerAdapter = TabViewPagerAdapter(this)
        tabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpFollow.adapter = tabViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLable = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tlFollow, binding.vpFollow) {tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }

}