package com.levgeogr.backendlessresearch

import android.app.Application
import android.content.Context
import com.backendless.Backendless
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        kodein = Kodein {
            bind<Context>() with singleton { this@App }
        }
        val backendlessAppID = this.resources.getString(R.string.backendless_app_id)
        val backendlessAndroidKey = this.resources.getString(R.string.backendless_android_key)
        val backendlessBaseUrl = this.resources.getString(R.string.backendless_base_url)
        Backendless.setUrl(backendlessBaseUrl)
        Backendless.initApp(applicationContext, backendlessAppID, backendlessAndroidKey)
    }

    companion object {
        lateinit var kodein: Kodein
    }
}