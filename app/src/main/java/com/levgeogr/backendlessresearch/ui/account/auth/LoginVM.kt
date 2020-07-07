package com.levgeogr.backendlessresearch.ui.account.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.levgeogr.backendlessresearch.utils.CustomVM
import kotlinx.coroutines.launch

class LoginVM(val view: ILoginView? = null) : CustomVM(), AsyncCallback<BackendlessUser> {

    val isAccountExists = MutableLiveData(false)
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val isLoading = MutableLiveData(false)

    private lateinit var user: BackendlessUser

    fun loginRegistrationButtonClicked() {
        if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            view?.incorrectForm()
            return
        }

        user = BackendlessUser()
        user.email = email.value
        user.password = password.value
        isLoading.postValue(true)

        if (isAccountExists.value!!) {
            login()
        } else {
            register()
        }
    }

    fun login() {
        ioScope.launch {
            Backendless.UserService.login(user.email, user.password, this@LoginVM)
            isLoading.postValue(false)
        }
    }

    fun register() {
        ioScope.launch {
            Backendless.UserService.register(user, this@LoginVM)
            isLoading.postValue(false)
        }
    }

    override fun handleFault(fault: BackendlessFault?) {
        Log.e("fault", "$fault")
        val errorLog = "code:${fault?.code}\nmessage:${fault?.message} \ndetail:${fault?.detail}"
        view?.showErrors(errorLog)
    }

    override fun handleResponse(response: BackendlessUser?) {
        view?.closeLoginView()
    }
}