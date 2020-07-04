package com.levgeogr.backendlessresearch.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.levgeogr.backendlessresearch.api.models.Person
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel(), AsyncCallback<Person> {
    fun addUser(name: String, age: Int) {
        val person = Person(name, age)
        GlobalScope.launch {
            val savedPerson = Backendless.Data.of(Person::class.java).save(person, this@HomeViewModel)
            Log.e("sad", "saved $savedPerson")
        }
    }

    override fun handleFault(fault: BackendlessFault?) {
        Log.e("Home VM", "fault: $fault")
    }

    override fun handleResponse(response: Person?) {
        Log.e("Home VM", "person saved $response")
    }
}