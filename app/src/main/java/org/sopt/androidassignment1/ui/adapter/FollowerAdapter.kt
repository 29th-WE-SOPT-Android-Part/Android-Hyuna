package org.sopt.androidassignment1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidassignment1.databinding.ItemFollowerlistBinding
import org.sopt.androidassignment1.data.local.FollowerData

class FollowerAdapter : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>(){
    val followerList = mutableListOf<FollowerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding = ItemFollowerlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    override fun getItemCount(): Int = followerList.size

    class FollowerViewHolder(private val binding: ItemFollowerlistBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data: FollowerData) {
            binding.name.text = data.name
            binding.introduction.text = data.introduction
        }
    }
}