package com.andresleonel09.ejerciciokotlin.app.`interface`

import com.andresleonel09.ejerciciokotlin.app.model.Contact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    companion object {
        const val BASE_URL = "https://private-d0cc1-iguanafixtest.apiary-mock.com/"
    }

    @get:GET("contacts")
    val contacts: Call<List<Contact>>

    @GET("contacts/{id}")
    fun contactById(@Path("id") id: String): Call<Contact>
}