package com.levgeogr.backendlessresearch.ui.account.auth

interface ILoginView {
    fun incorrectForm()
    fun closeLoginView()
    fun showErrors(error: String)
}