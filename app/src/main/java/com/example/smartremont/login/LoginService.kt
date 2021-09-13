package com.example.smartremont.login

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LoginService {

    private val BASE_URL = "https://camunda.cheesenology.kz"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(LoginAPI::class.java)

    fun loginResponse(loginContent: LoginContent): Single<LoginResponse>{
        return api.login(loginContent)
    }

}