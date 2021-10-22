package org.sopt.androidassignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidassignment1.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickLogIn()
        clickSignIn()
    }

    private fun clickLogIn(){
        val id = binding.idInput.text
        val pw = binding.pwInput.text

        binding.btnLogin.setOnClickListener {
            if (id.isNotEmpty() && pw.isNotEmpty()) {
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickSignIn(){
        binding.btnSignin.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }
}

