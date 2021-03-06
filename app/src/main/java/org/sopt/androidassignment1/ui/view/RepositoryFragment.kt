package org.sopt.androidassignment1.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.androidassignment1.databinding.FragmentRepositoryBinding
import org.sopt.androidassignment1.ui.adapter.RepositoryAdapter
import org.sopt.androidassignment1.data.local.RepositoryData

class RepositoryFragment : Fragment() {
    private var _binding : FragmentRepositoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    private fun initAdapter(){
        repositoryAdapter = RepositoryAdapter()
        binding.repositoryRecyclerView.adapter = repositoryAdapter
        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryData("안드로이드 과제 레포지토리", "안드로이트 파트 과제"),
                RepositoryData("iOS 과제 레포지토리", "iOS 파트 과제"),
                RepositoryData("서버 과제 레포지토리", "서버 파트 과제"),
                RepositoryData("웹 과제 레포지토리", "웹 파트 과제"),
                RepositoryData("디자인 과제 레포지토리", "디자인 파트 과제"),
                RepositoryData("기획 과제 레포지토리", "기획 파트 과제"),
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

