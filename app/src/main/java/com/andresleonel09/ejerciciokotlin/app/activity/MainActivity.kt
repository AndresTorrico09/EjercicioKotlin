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
import com.andresleonel09.ejerciciokotlin.app.view_model.ContactViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rvContactos)
        var adapter: ContactAdapter?

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get the ViewModel.
        val mModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // Create the observer which updates the UI.
        val contactObserver = Observer<List<Contact>> {
            // Update the UI, in this case, a adapter.
            adapter = ContactAdapter(this@MainActivity, contactList = it!!)
            recyclerView.adapter = adapter
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getContacts.observe(this, contactObserver)
    }
}
