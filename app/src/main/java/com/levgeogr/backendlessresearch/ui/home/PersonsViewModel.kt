package com.levgeogr.backendlessresearch.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.levgeogr.backendlessresearch.api.models.Person
import com.levgeogr.backendlessresearch.utils.CustomVM
import kotlinx.coroutines.launch


class PersonsViewModel : CustomVM(), AsyncCallback<Person> {

    val persons = MutableLiveData(mutableListOf<Person>())
    var selectedPerson = Person()
    val isRefreshing = MutableLiveData(false)

    init {
        load()
    }

    fun load() {
        isRefreshing.postValue(true)
        ioScope.launch {
            val newPersons = Backendless.Data.of(Person::class.java).find()
            persons.postValue(newPersons)
            isRefreshing.postValue(false)
        }
    }

    fun addPerson(name: String, age: Int) {
        val person = Person(name, age)
        ioScope.launch {
            val savedPerson =
                Backendless.Data.of(Person::class.java).save(person, this@PersonsViewModel)
        }
        load()
    }

    fun updatePerson(name: String, age: Int) {
        selectedPerson.name = name
        selectedPerson.age = age
        ioScope.launch {
            Backendless.Data.of(Person::class.java).save(selectedPerson, this@PersonsViewModel)
        }
    }

    fun deletePerson(person: Person) {
        ioScope.launch {
            Backendless.Data.of(Person::class.java).remove(person)
        }
        load()
    }

    override fun handleFault(fault: BackendlessFault?) {
        Log.e("Home VM", "fault: $fault")
    }

    override fun handleResponse(person: Person?) {
        Log.e("Home VM", "response: $person \n ${person?.name} ${person?.age}")
        load()
    }
}