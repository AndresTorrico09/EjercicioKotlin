package com.andresleonel09.ejerciciokotlin.app.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.andresleonel09.ejerciciokotlin.R
import com.andresleonel09.ejerciciokotlin.app.model.Contact
import com.bumptech.glide.Glide

class ContactAdapter(internal var mCtx: Context, internal var contactList: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_layout, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        Glide.with(mCtx)
                .load(contact.photo)
                .into(holder.imageView)

        holder.textView.text = contact.first_name + " " + contact.last_name
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView
        var textView: TextView

        init {

            imageView = itemView.findViewById(R.id.imageView)
            textView = itemView.findViewById(R.id.textView)
        }
    }
}