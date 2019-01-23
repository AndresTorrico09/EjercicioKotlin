package com.andresleonel09.ejerciciokotlin.app.view_model

import com.andresleonel09.ejerciciokotlin.app.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.andresleonel09.ejerciciokotlin.app.`interface`.Api
import com.andresleonel09.ejerciciokotlin.app.model.Contact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ContactViewModel : ViewModel() {

    //this is the data that we will fetch asynchronously 
    var contactList: MutableLiveData<List<Contact>>? = null

    //we will call this method to get the data
    //if the list is null 
    //we will load it asynchronously from server in this method
    //finally we will return the list
    val getContacts: LiveData<List<Contact>>
        get() {
            if (contactList == null) {
                contactList = MutableLiveData()
                loadContacts()
            }
            return contactList as MutableLiveData<List<Contact>>
        }

    //This method is using Retrofit to get the JSON data from URL 
    private fun loadContacts() {
        val retrofit = Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(Api::class.java)
        val call = api.contacts

        call.enqueue(object : Callback<List<Contact>> {
            override fun onResponse(call: Call<List<Contact>>, response: Response<List<Contact>>) {
                Collections.sort(response.body()) {
                    object1, object2 -> object1.first_name!!.compareTo(object2.first_name!!) }

                //finally we are setting the list to our MutableLiveData
                contactList!!.value = response.body()
            }

            override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                    var tt = t
            }
        })
    }
}