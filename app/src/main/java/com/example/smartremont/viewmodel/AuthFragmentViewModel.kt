package com.example.smartremont.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.smartremont.login.LoginContent
import com.example.smartremont.login.LoginResponse
import com.example.smartremont.login.LoginService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class AuthFragmentViewModel(application: Application): BaseViewModel(application) {

    private val loginService = LoginService()
    private var disposable = CompositeDisposable()

    var signedIn = MutableLiveData<Boolean>()
    var signedInLoading = MutableLiveData<Boolean>()

    fun signIn(email : String, password: String){
        signedInLoading.value = true
        var loginContent = LoginContent(email, password)
        disposable.add(
            loginService.loginResponse(loginContent)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<LoginResponse>(){
                    override fun onSuccess(t: LoginResponse) {
                        println("succeed")
                        signedInLoading.value = false
                        signedIn.value = true
                    }

                    override fun onError(e: Throwable) {
                        println("unsucceed")
                        Toast.makeText(getApplication(),e.localizedMessage,Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                        signedInLoading.value = false
                        signedIn.value = false
                    }

                })
        )
    }
    fun storeInSQLite(){

        launch {

        }
    }


}