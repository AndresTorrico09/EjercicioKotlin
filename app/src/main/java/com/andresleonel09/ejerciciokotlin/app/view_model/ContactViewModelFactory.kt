package com.andresleonel09.ejerciciokotlin.app.view_model

import android.arch.lifecycle.ViewModel
import android.app.Application
import android.arch.lifecycle.ViewModelProvider


class ContactViewModelFactory (private val mApplication: Application, private val mParam: String) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactViewModel(mApplication, mParam) as T
    }
}