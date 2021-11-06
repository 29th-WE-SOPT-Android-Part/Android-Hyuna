package org.sopt.androidassignment1.BaseFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.sopt.androidassignment1.Follower.FollowerFragment
import org.sopt.androidassignment1.R
import org.sopt.androidassignment1.Repository.RepositoryFragment
import org.sopt.androidassignment1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initTransaction()
        initImage()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initTransaction(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.container_recycle,followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            binding.btnFollower.isSelected = true
            binding.btnRepository.isSelected = false
            var position = childFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle, followerFragment)
            position.commit()
        }

        binding.btnRepository.setOnClickListener {
            binding.btnFollower.isSelected = false
            binding.btnRepository.isSelected = true
            var position = childFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle,repositoryFragment)
            position.commit()
        }
    }
    private fun initImage() {
        Glide.with(this)
            .load(R.drawable.image)
            .circleCrop()
            .into(binding.ivProfile)
    }
}
