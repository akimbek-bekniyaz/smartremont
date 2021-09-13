package com.example.smartremont.login

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {
    //   https://camunda.cheesenology.kz/auth/login/
    @POST("/auth/login/")
    fun login(@Body loginContent: LoginContent): Single<LoginResponse>

}