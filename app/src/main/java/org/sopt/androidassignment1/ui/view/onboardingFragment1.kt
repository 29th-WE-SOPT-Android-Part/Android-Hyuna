package org.sopt.androidassignment1.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.sopt.androidassignment1.R
import org.sopt.androidassignment1.databinding.FragmentOnboarding1Binding

class onboardingFragment1 : Fragment() {
    private var _binding : FragmentOnboarding1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding1Binding.inflate(inflater)

        binding.btnNext.setOnClickListener{
            findNavController().navigate(R.id.action_onboardingFragment1_to_onboardingFragment2)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}