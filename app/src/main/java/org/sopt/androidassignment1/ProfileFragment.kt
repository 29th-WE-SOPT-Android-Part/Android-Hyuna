package org.sopt.androidassignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.sopt.androidassignment1.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initTransactionEvent()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initTransactionEvent(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.container_recycle,followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            var position = childFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle, followerFragment)
            position.commit()
        }

        binding.btnRepository.setOnClickListener {
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
