package com.levgeogr.backendlessresearch.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class CoroutineHelper {

    private val uiJob = SupervisorJob()
    private val ioJob = SupervisorJob()

    val uiContext: CoroutineContext = Dispatchers.Main + uiJob
    val ioContext: CoroutineContext = Dispatchers.IO + ioJob
}