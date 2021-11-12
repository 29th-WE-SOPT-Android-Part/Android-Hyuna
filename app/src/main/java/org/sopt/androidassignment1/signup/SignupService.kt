package org.sopt.androidassignment1.signup

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignupService {
    @Headers("Content-Type:application/json")
    @POST("user/signup")

    fun postSignup(
        @Body body: RequestSignupData
    ) : Call<ResponseSignupData>

}