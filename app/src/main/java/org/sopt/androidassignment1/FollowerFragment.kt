package org.sopt.androidassignment1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.androidassignment1.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {
    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter(){
        followerAdapter = FollowerAdapter()
        binding.followerRecyclerView.adapter = followerAdapter
        followerAdapter.followerList.addAll(
            listOf(
                FollowerData("문다빈", "안드로이드 파트장"),
                FollowerData("장혜령", "iOS 파트장"),
                FollowerData("김우영", "서버 파트장"),
                FollowerData("이성현", "디자인 파트장"),
                FollowerData("김송현", "운영팀장"),
            )
        )
        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

