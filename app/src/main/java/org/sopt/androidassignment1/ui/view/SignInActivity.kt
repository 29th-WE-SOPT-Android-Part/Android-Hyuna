package org.sopt.androidassignment1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.androidassignment1.util.ViewExt.shortToast
import org.sopt.androidassignment1.data.remote.RequestSigninData
import org.sopt.androidassignment1.data.remote.ResponseSigninData
import org.sopt.androidassignment1.data.remote.ServiceCreator
import org.sopt.androidassignment1.databinding.ActivitySigninBinding
import org.sopt.androidassignment1.util.SOPTSharedPreferences
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
        initClickEvent()
        isAutoLogin()
    }

    private fun initClickEvent(){
        binding.btnAutoLogin.setOnClickListener{
            binding.btnAutoLogin.isSelected = !binding.btnAutoLogin.isSelected

            SOPTSharedPreferences.setAutoLogin(this, binding.btnAutoLogin.isSelected)
        }
    }

    private fun isAutoLogin(){
        if(SOPTSharedPreferences.getAutoLogin(this)) {
            shortToast("자동로그인 완료")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
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

    private fun clickSignUp() {
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

        val call: Call<ResponseSigninData> = ServiceCreator.SAMPLE_SERVICE.postLogin(requestSigninData)

        call.enqueue(object : Callback<ResponseSigninData> {
            override fun onResponse(
                call: Call<ResponseSigninData>,
                response: Response<ResponseSigninData>
            ) {
                if(response.isSuccessful) {
                    shortToast("${response.body()?.data?.name}님 반갑습니다")
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
                else {
                    Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSigninData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}

