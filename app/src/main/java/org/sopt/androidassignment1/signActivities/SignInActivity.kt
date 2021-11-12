package org.sopt.androidassignment1.signActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidassignment1.MainActivity
import org.sopt.androidassignment1.signIn.RequestSigninData
import org.sopt.androidassignment1.signIn.ResponseSigninData
import org.sopt.androidassignment1.ServiceCreator
import org.sopt.androidassignment1.databinding.ActivitySigninBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)

        setContentView(binding.root)

        clickLogIn()
        clickSignUp()
    }

    private fun clickLogIn(){
        val id = binding.idInput.text
        val pw = binding.pwInput.text

        binding.btnLogin.setOnClickListener {
            if (id.isNotEmpty() && pw.isNotEmpty()) {
                initNetwork()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickSignUp(){
        binding.btnSignup.setOnClickListener {
            val signUpIntent = Intent(this, SignUpActivity::class.java)
            startActivity(signUpIntent)
        }
    }


    private fun initNetwork() {
        val requestSigninData = RequestSigninData(
            email = binding.idInput.text.toString(),
            password = binding.pwInput.text.toString()
        )

        val call: Call<ResponseSigninData> = ServiceCreator.signinService.postLogin(requestSigninData)

        call.enqueue(object : Callback<ResponseSigninData> {
            override fun onResponse(
                call: Call<ResponseSigninData>,
                response: Response<ResponseSigninData>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@SignInActivity,"${response.body()?.data?.name}님 반갑습니다",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
                else {
                    Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSigninData>, t: Throwable) {

            }
        })
    }
}
