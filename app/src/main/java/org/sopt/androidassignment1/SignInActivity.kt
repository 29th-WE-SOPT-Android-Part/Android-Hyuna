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

        var id = binding.idInput.text
        var pw = binding.pwInput.text

        binding.btnLogin.setOnClickListener {
            if (id.isNotEmpty() && pw.isNotEmpty()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else if (id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnSignin.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            startActivity(intent2)
        }

        setContentView(binding.root)
    }
}

