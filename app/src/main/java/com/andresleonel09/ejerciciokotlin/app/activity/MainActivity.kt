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
import android.view.View.OnAttachStateChangeListener
import android.view.View


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var adapter: ContactAdapter? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = this.findViewById(R.id.rvContactos)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                adapter!!.filter.filter(query)
                return false
            }
        })

        searchView.setIconifiedByDefault(true)
        //Reset ViewModel
        searchView.setOnCloseListener {
            searchView.setQuery("", false)
            searchView.clearFocus()
            getViewModel()
            false
        }
        searchView.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewDetachedFromWindow(arg0: View) {
                getViewModel()
            }

            override fun onViewAttachedToWindow(arg0: View) {
                // search was opened
            }
        })

        return true
    }

    //Get View Model and update UI
    private fun getViewModel() {
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


    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
