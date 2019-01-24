package com.andresleonel09.ejerciciokotlin.app.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.andresleonel09.ejerciciokotlin.R
import com.andresleonel09.ejerciciokotlin.app.activity.DetailActivity
import com.andresleonel09.ejerciciokotlin.app.model.Contact
import com.squareup.picasso.Picasso
import java.util.*


class ContactAdapter(private var mCtx: Context, internal var contactList: List<Contact>)
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), Filterable {

    private var contactFilter: ContactFilter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(com.andresleonel09.ejerciciokotlin.R.layout.carview_contacto, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        Picasso.get()
                .load(contact.thumb)
                .resize(100, 100)
                .centerCrop()
                .into(holder.imgThumb)

        holder.tvNombre.text = contact.first_name + " " + contact.last_name

        holder.itemView.setOnClickListener {
            val intent = Intent(mCtx, DetailActivity::class.java)
            intent.putExtra("idContact", contact.user_id)
            mCtx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgThumb: ImageView = itemView.findViewById(R.id.imgThumb)
        var tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
    }

    override fun getFilter(): Filter {
        if (contactFilter == null) {
            contactFilter = ContactFilter()
        }

        return contactFilter as ContactFilter
    }

    private inner class ContactFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): Filter.FilterResults {
            val filterResults = Filter.FilterResults()
            if (constraint != null && constraint.isNotEmpty()) {
                val tempList : MutableList<Contact> = ArrayList()

                // search content in friend list
                for (contact in contactList) {
                    if (contact.first_name!!.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(contact)
                    }
                }

                filterResults.count = tempList.size
                filterResults.values = tempList
            } else {
                filterResults.count = contactList.size
                filterResults.values = contactList
            }

            return filterResults
        }

        override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
            addAllItems(filterResults.values as List<Contact>)
        }
    }

    fun addAllItems(items: List<Contact>) {
        if (items.isNotEmpty()) {
            Collections.sort(items) { object1, object2 ->
                object1.first_name!!.compareTo(object2.first_name!!) }
        }
        val tempList : MutableList<Contact> = ArrayList()
        contactList = tempList
        contactList += items

        notifyDataSetChanged()
    }
}