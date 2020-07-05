package com.levgeogr.backendlessresearch

import android.app.Application
import android.content.Context
import com.backendless.Backendless
import com.levgeogr.backendlessresearch.utils.JsonDataStorage
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import com.levgeogr.backendlessresearch.utils.PrefStorage
import com.levgeogr.backendlessresearch.utils.Register

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            bind<Context>() with singleton { this@App }
            bind<PrefStorage>() with singleton {
                PrefStorage(
                    instance()
                )
            }
            bind<JsonDataStorage>() with singleton {
                JsonDataStorage(instance())
            }
        }
        val backendlessAppID = this.resources.getString(R.string.backendless_app_id)
        val backendlessAndroidKey = this.resources.getString(R.string.backendless_android_key)
        val backendlessBaseUrl = this.resources.getString(R.string.backendless_base_url)
        Backendless.setUrl(backendlessBaseUrl)
        Backendless.initApp(applicationContext, backendlessAppID, backendlessAndroidKey)

        val prefStorage by kodein.instance<PrefStorage>()
        val isDeviceRegistered = prefStorage.isDeviceRegistered()
        if (!isDeviceRegistered) {
            val register = Register()
            register.startRegistration()
            prefStorage.setIsDeviceRegistered(true)
        }
    }

    companion object {
        lateinit var kodein: Kodein
    }
}