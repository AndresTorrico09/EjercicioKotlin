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
import android.support.v7.widget.SearchView
import com.andresleonel09.ejerciciokotlin.app.view_model.ContactViewModel
import android.app.SearchManager
import android.content.Context
import android.view.Menu
import com.andresleonel09.ejerciciokotlin.app.view_model.ContactViewModelFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = this.findViewById(R.id.rvContactos)
        var adapter: ContactAdapter?

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get the ViewModel.
        //val mModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        val myViewModel = ViewModelProviders.of(this,
                ContactViewModelFactory(this.application, "")).get(ContactViewModel::class.java)

        // Create the observer which updates the UI.
        val contactObserver = Observer<List<Contact>> { list ->
            // Update the UI, in this case, a adapter.
            adapter = ContactAdapter(this@MainActivity, contactList = list!!)
            recyclerView.adapter = adapter
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myViewModel.getContacts.observe(this, contactObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)

/*        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                return false
            }
        })
*/
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
