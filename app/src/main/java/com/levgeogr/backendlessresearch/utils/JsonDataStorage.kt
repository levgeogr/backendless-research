package com.levgeogr.backendlessresearch.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class JsonDataStorage(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun <Type> put(key: String, data: Type) {
        sharedPreferences.edit().putString(key, Gson().toJson(data)).apply()
    }

    fun putBoolean(key: String, data: Boolean) {
        sharedPreferences.edit().putBoolean(key, data).apply()
    }

    fun putLong(key: String, data: Long) {
        sharedPreferences.edit().putLong(key, data).apply()
    }

    fun putInt(key: String, data: Int) {
        sharedPreferences.edit().putInt(key, data).apply()
    }

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun getLong(key: String) = sharedPreferences.getLong(key, 0L)

    fun getInt(key: String) = sharedPreferences.getInt(key, 0)

    fun getJson(key: String, default: String = ""): String? = try {
        sharedPreferences.getString(key, default)
    } catch (e: Exception) {
        default
    }
}
