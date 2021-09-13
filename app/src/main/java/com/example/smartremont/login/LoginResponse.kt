package com.example.smartremont.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token : Token,
    @SerializedName("user") val user : User
)

data class Token (

    @SerializedName("refresh") val refresh : String,
    @SerializedName("access") val access : String
)

data class User (

    @SerializedName("employee_id") val employee_id : Int,
    @SerializedName("image_url") val image_url : String,
    @SerializedName("email") val email : String,
    @SerializedName("fio") val fio : String,
    @SerializedName("bpm_position") val bpm_position : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("phone_number") val phone_number : String
)