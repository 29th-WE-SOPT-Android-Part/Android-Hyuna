package org.sopt.androidassignment1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidassignment1.R
import org.sopt.androidassignment1.databinding.ActivitySettingBinding
import org.sopt.androidassignment1.databinding.ActivitySigninBinding
import org.sopt.androidassignment1.util.SOPTSharedPreferences

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        clickAutoLogin()
    }

    private fun clickAutoLogin() {
        binding.btnAutoLogin.setOnClickListener {
            SOPTSharedPreferences.removeAutoLogin(this,true)
            SOPTSharedPreferences.clearAutoLogin(this, true)
        }
    }
}