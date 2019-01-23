package com.andresleonel09.ejerciciokotlin.app.activity

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.andresleonel09.ejerciciokotlin.R
import android.support.v7.widget.RecyclerView
import com.andresleonel09.ejerciciokotlin.app.adapter.ContactAdapter
import com.andresleonel09.ejerciciokotlin.app.model.Contact
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.Nullable
import com.andresleonel09.ejerciciokotlin.app.view_model.ContactViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        var adapter: ContactAdapter?
        var contactList: List<Contact>? = null

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val model = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        model.getContacts.observe(this, Observer<List<Contact>> {
            fun onChanged(@Nullable contactList: List<Contact>) {
                adapter = ContactAdapter(this@MainActivity, contactList)
                recyclerView.adapter = adapter
            }
        })
    }
}
