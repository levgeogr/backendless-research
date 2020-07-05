package com.levgeogr.backendlessresearch.ui.home

import com.levgeogr.backendlessresearch.api.models.Person

interface PersonClickListener {
    fun personClicked(person: Person)
    fun personDelete(person: Person)
}