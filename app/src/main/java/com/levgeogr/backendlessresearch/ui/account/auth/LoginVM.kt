package com.levgeogr.backendlessresearch.ui.account.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.levgeogr.backendlessresearch.App.Companion.kodein
import com.levgeogr.backendlessresearch.R
import com.levgeogr.backendlessresearch.utils.CustomVM
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class LoginVM(val view: ILoginView? = null) : CustomVM(), AsyncCallback<BackendlessUser> {

    val isAccountExists = MutableLiveData(false)
    val email = MutableLiveData(String())
    val password = MutableLiveData(String())
    val isLoading = MutableLiveData(false)
    val errorsText = MutableLiveData(String())

    private val context by kodein.instance<Context>()

    private lateinit var user: BackendlessUser

    fun loginRegistrationButtonClicked() {
        if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
//            view?.incorrectForm()
            errorsText.postValue(context.getString(R.string.fill_all_fields))
            return
        }

        user = BackendlessUser()
        user.apply {
            email = this@LoginVM.email.value
            password = this@LoginVM.password.value
        }
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
//        view?.showErrors(errorLog)
        errorsText.postValue(errorLog)
    }

    override fun handleResponse(response: BackendlessUser?) {
        errorsText.postValue("")
        view?.closeLoginView()
    }
}