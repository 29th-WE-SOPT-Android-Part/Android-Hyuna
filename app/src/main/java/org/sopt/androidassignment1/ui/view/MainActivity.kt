package org.sopt.androidassignment1.ui.view

import android.os.Bundle
import org.sopt.androidassignment1.R
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import org.sopt.androidassignment1.ui.view.CameraFragment
import org.sopt.androidassignment1.ui.view.HomeFragment
import org.sopt.androidassignment1.ui.view.ProfileFragment
import org.sopt.androidassignment1.ui.adapter.ViewPagerAdapter
import org.sopt.androidassignment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ViewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initAdapter()
        initBottomNavigation()

        setContentView(binding.root)
    }

    private fun initAdapter(){
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())

        ViewPagerAdapter = ViewPagerAdapter(this)
        ViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpSample.adapter = ViewPagerAdapter
    }


    private fun initBottomNavigation(){
        binding.vpSample.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvSample.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvSample.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_profile -> {
                    binding.vpSample.currentItem = FIRST_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                R.id.menu_home -> {
                    binding.vpSample.currentItem = SECOND_FRAGMENT
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.vpSample.currentItem = THIRD_FRAGMENT
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}