package org.sopt.androidassignment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidassignment1.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        initTransactionEvent()
        setContentView(binding.root)
    }

    private fun initTransactionEvent(){
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        supportFragmentManager.beginTransaction().add(R.id.container_recycle,followerFragment).commit()

        binding.btnFollower.setOnClickListener {
            var position = supportFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle, followerFragment)
            position.commit()
        }

        binding.btnRepository.setOnClickListener {
            var position = supportFragmentManager.beginTransaction()
            position.replace(R.id.container_recycle,repositoryFragment)
            position.commit()
        }


    }
}
