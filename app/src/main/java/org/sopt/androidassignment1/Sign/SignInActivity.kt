package org.sopt.androidassignment1.Sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidassignment1.MainActivity
import org.sopt.androidassignment1.databinding.ActivitySigninBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener{
            //initNetwork()
        }

        setContentView(binding.root)

        clickLogIn()
        clickSignUp()
    }

    private fun clickLogIn(){
        val id = binding.idInput.text
        val pw = binding.pwInput.text

        binding.btnLogin.setOnClickListener {
            if (id.isNotEmpty() && pw.isNotEmpty()) {
                val homeIntent = Intent(this, MainActivity::class.java)
                startActivity(homeIntent)
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickSignUp(){
        binding.btnSignup.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }
/*
    private fun initNetwork() {
        val requestLoginData = RequestLoginData(
            email = binding.id.text.toString(), //여기랑 아래 pw binding 뒤에 id랑 pw 맞는지 확인해보기
            password = binding.pw.text.toString()
        )

        val call: Call<ResponseLoginData> = ServiceCreator.sampleService.postLogin(requestLoginData)

        call.enqueue(object : Callback<ResponseLoginData>{
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@LogInActivity,"${response.body()?.data?.name}님 반갑습니다",Toast.LENGTH_SHORT)
                    startActivity(Intent(this@LogInActivity, MainActivity::class.java))
                }
                else {

                }
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {

            }
        })
    }

 */
}

