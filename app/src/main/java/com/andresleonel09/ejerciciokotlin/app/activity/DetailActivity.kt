package com.andresleonel09.ejerciciokotlin.app.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.andresleonel09.ejerciciokotlin.R
import android.widget.TextView
import android.widget.ImageView
import com.andresleonel09.ejerciciokotlin.app.model.Contact
import com.andresleonel09.ejerciciokotlin.app.view_model.ContactViewModel
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgFoto: ImageView         = findViewById(R.id.imgFoto)
        val tvNombre: TextView         = findViewById(R.id.tvNombre)
        val tvPhoneHome: TextView      = findViewById(R.id.tvPhoneHome)
        val tvPhoneCellphone: TextView = findViewById(R.id.tvPhoneCellphone)
        val tvPhoneOffice: TextView    = findViewById(R.id.tvPhoneOffice)
        val tvBirthDate: TextView      = findViewById(R.id.tvBirthDate)
        val tvAdressHome: TextView     = findViewById(R.id.tvAdressHome)
        val tvAdressWork: TextView     = findViewById(R.id.tvAdressWork)

        val idContact = intent.getStringExtra("idContact")

        // Get the ViewModel.
        val mModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // Create the observer which updates the UI.
        val contactByIdObserver = Observer<Contact> { contact ->
            Picasso.get().load(contact!!.photo).resize(200, 200).centerCrop().into(imgFoto)
            tvNombre.text         = contact.first_name
            tvPhoneHome.text      = contact.phones!![0].number
            tvPhoneCellphone.text = contact.phones!![1].number
            tvPhoneOffice.text    = contact.phones!![2].number
            tvBirthDate.text      = contact.birthDate
            //tvAdressHome.text     = contact.addresses!![0].home
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getContactById.observe(this, contactByIdObserver)
    }
}
