package org.sopt.androidassignment1.signActivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.androidassignment1.ServiceCreator
import org.sopt.androidassignment1.databinding.ActivitySignupBinding
import org.sopt.androidassignment1.signup.RequestSignupData
import org.sopt.androidassignment1.signup.ResponseSignupData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickFinish()
    }

    private fun clickFinish(){
        var id = binding.idInputSignup.text
        var pw = binding.pwInputSignup.text
        var name = binding.nameInputSignup.text

        binding.btnFinish.setOnClickListener {
            if (id.isNotEmpty() && pw.isNotEmpty() && name.isNotEmpty()) {
                initNetwork()
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initNetwork() {
        val requestSignupData = RequestSignupData(
            name = binding.nameInputSignup.text.toString(),
            email = binding.idInputSignup.text.toString(),
            password = binding.pwInputSignup.text.toString()
        )

        val call: Call<ResponseSignupData> = ServiceCreator.SAMPLE_SERVICE.postSignup(requestSignupData)

        call.enqueue(object : Callback<ResponseSignupData> {
            override fun onResponse(
                call: Call<ResponseSignupData>,
                response: Response<ResponseSignupData>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@SignUpActivity,"${response.body()?.data?.name}님 회원가입 완료되었습니다",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    finish()
                }
                else {
                    Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

}


