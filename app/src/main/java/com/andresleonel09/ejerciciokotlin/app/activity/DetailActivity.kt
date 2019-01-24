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
        val tvAddressHome: TextView    = findViewById(R.id.tvAdressHome)
        val tvAddressWork: TextView    = findViewById(R.id.tvAdressWork)

        val idContact = intent.getStringExtra("idContact")

        // Get the ViewModel.
        val mModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // Create the observer which updates the UI.
        val contactByIdObserver = Observer<Contact> { contact ->
            Picasso.get().load(contact!!.photo).resize(200, 200).centerCrop().into(imgFoto)

            tvNombre.text = contact.first_name + " " + contact.last_name
            tvPhoneHome.text = String.format(resources.getString(R.string.phone_home), if (contact.phones!![0].number == null) "-" else contact.phones!![0].number.toString())
            tvPhoneCellphone.text = String.format(resources.getString(R.string.phone_cellphone), if (contact.phones!![1].number == null) "-" else contact.phones!![1].number.toString())
            tvPhoneOffice.text = String.format(resources.getString(R.string.phone_office), if (contact.phones!![2].number == null) "-" else contact.phones!![2].number.toString())
            tvBirthDate.text = String.format(resources.getString(R.string.birth_date), contact.birth_date)
            tvAddressHome.text = String.format(resources.getString(R.string.address_home), if (contact.addresses!![0].home == null) "-" else contact.addresses!![0].home)
            tvAddressWork.text = String.format(resources.getString(R.string.address_work), if (contact.addresses!![0].work == null) "-" else contact.addresses!![0].work)
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getContactById.observe(this, contactByIdObserver)
    }
}
