package com.alanturing.cpifp.whatsappclone.chat.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository @Inject constructor() {

    private val _contacts: MutableList<Contact> = mutableListOf()

    val contacts: List<Contact>
        get() = _contacts.toList()

    init {
        _contacts.add(Contact(1,"Joseph","San Juan"))
        _contacts.add(Contact(2,"Diego","Leon"))
        _contacts.add(Contact(3,"Verónica","González"))
    }
}