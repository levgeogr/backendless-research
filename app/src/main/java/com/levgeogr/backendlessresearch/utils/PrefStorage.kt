package com.levgeogr.backendlessresearch.utils

import android.content.Context

const val IS_REGISTERED = "is_registered"

class PrefStorage(context: Context) {

    private val prefs = JsonDataStorage(context)

    fun isDeviceRegistered(): Boolean = prefs.getBoolean(IS_REGISTERED)

    fun setIsDeviceRegistered(isRegistered: Boolean) {
        prefs.putBoolean(IS_REGISTERED, isRegistered)
    }

}
