package org.sopt.androidassignment1.signIn

import com.google.gson.annotations.SerializedName

data class RequestSigninData(
    @SerializedName("email")
    val email: String,
    val password: String
)
