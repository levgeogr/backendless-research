package com.levgeogr.backendlessresearch.utils

import android.content.Context
import android.util.Log
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.push.DeviceRegistrationResult
import com.levgeogr.backendlessresearch.App.Companion.kodein
import org.kodein.di.generic.instance

class Register {
    val context by kodein.instance<Context>()

    fun startRegistration() {
        val channels: MutableList<String> = ArrayList()
        channels.add("default")
        Backendless.Messaging.registerDevice(
            channels,
            object : AsyncCallback<DeviceRegistrationResult?> {
                override fun handleResponse(response: DeviceRegistrationResult?) {
                    Log.e("REGISTRATION", "Device registered: $response")
                }

                override fun handleFault(fault: BackendlessFault) {
                    Log.e("REGISTRATION", "registration fault $fault")
                }
            })
    }
}