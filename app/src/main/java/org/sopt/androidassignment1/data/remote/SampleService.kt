package org.sopt.androidassignment1.data.remote

import org.sopt.androidassignment1.data.remote.RequestSigninData
import org.sopt.androidassignment1.data.remote.ResponseSigninData
import org.sopt.androidassignment1.data.remote.RequestSignupData
import org.sopt.androidassignment1.data.remote.ResponseSignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SampleService {
    @Headers("Content-Type:application/json")
    @POST("user/login")

    fun postLogin(
        @Body body : RequestSigninData
    ) : Call<ResponseSigninData>

    @Headers("Content-Type:application/json")
    @POST("user/signup")

    fun postSignup(
        @Body body : RequestSignupData
    ) : Call<ResponseSignupData>
}