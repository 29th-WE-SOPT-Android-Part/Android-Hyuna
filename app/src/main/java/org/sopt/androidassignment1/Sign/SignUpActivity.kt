package org.sopt.androidassignment1.Sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidassignment1.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickFinishSignIn()
    }

    private fun clickFinishSignIn(){
        var id = binding.idInputSignup.text
        var pw = binding.pwInputSignup.text
        var name = binding.nameInputSignup.text

        binding.btnFinish.setOnClickListener {
            if (id.isNotEmpty() && pw.isNotEmpty() && name.isNotEmpty()) {
                finish()
            } else if (id.isEmpty() || pw.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
