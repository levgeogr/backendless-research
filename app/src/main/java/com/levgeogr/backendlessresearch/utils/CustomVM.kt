package com.levgeogr.backendlessresearch.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren

abstract class CustomVM : ViewModel() {

    private val coroutineHelper = CoroutineHelper()

    protected val uiScope = CoroutineScope(coroutineHelper.uiContext)
    protected val ioScope = CoroutineScope(coroutineHelper.ioContext)

    open fun onDestroy() {
        coroutineHelper.uiContext.cancelChildren()
        cancelRequest()
    }

    fun cancelRequest() {
        coroutineHelper.ioContext.cancelChildren()
    }
}