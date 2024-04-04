package com.alanturing.cpifp.whatsappclone.chat.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alanturing.cpifp.whatsappclone.chat.data.Contact
import com.alanturing.cpifp.whatsappclone.databinding.ContactListItemBinding

class ContactListAdapter(private val toChat: (View, Contact)-> Unit):ListAdapter<Contact,ContactListAdapter.ContactListItemViewHolder>(ContactDiff) {

    inner class ContactListItemViewHolder(private val binding:ContactListItemBinding):ViewHolder(binding.root) {
        fun bindTo(contact:Contact) {
            binding.contactName.text = contact.name
            binding.contactSurname.text = contact.surname
            binding.root.setOnClickListener {
                toChat(binding.root,contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListItemViewHolder {
        val binding: ContactListItemBinding = ContactListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactListItemViewHolder, position: Int) = holder.bindTo(getItem(position))

    object ContactDiff:DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
    }
}