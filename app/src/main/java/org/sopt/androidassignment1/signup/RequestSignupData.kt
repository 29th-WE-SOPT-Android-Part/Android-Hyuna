package org.sopt.androidassignment1.signup

import com.google.gson.annotations.SerializedName

data class RequestSignupData(
    @SerializedName("email")
    val name: String,
    val email: String,
    val password: String
)
