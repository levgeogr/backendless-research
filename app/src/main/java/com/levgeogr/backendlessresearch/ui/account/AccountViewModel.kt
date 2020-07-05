package com.levgeogr.backendlessresearch.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levgeogr.backendlessresearch.utils.CustomVM

class AccountViewModel : CustomVM() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}