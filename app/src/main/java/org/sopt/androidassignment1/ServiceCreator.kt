package org.sopt.androidassignment1

import org.sopt.androidassignment1.signIn.SigninService
import org.sopt.androidassignment1.signup.SignupService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"

    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signinService: SigninService = retrofit.create(SigninService::class.java)
    val signupService: SignupService = retrofit.create(SignupService::class.java)

}