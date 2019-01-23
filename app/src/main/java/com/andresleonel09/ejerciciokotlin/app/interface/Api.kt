package com.andresleonel09.ejerciciokotlin.app.`interface`

import com.andresleonel09.ejerciciokotlin.app.model.Contact
import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @get:GET("contacts")
    val contacts: Call<List<Contact>>

    companion object {
        const val BASE_URL = "https://private-d0cc1-iguanafixtest.apiary-mock.com/"
    }
}