package com.example.smartremont.login

import com.google.gson.annotations.SerializedName

data class LoginContent(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
)
