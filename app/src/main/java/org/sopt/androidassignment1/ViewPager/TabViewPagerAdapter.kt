package org.sopt.androidassignment1.ViewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.sopt.androidassignment1.BaseFragment.HomeFragment

class TabViewPagerAdapter(fragmentActivity: HomeFragment) : FragmentStateAdapter(fragmentActivity) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}