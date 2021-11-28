package org.sopt.androidassignment1.signIn

data class ResponseSigninData(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : Data
) {
    data class Data(
        val id : Int,
        val name : String,
        val email : String
    )
}
