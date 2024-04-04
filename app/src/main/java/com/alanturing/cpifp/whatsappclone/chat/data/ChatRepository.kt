package com.alanturing.cpifp.whatsappclone.chat.data

import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ChatRepository @Inject constructor() {

    private val _chats: MutableList<Chat> = mutableListOf()
    val chats: List<Chat>
        get() = _chats.toList()

    fun createChat(contact:Contact) {
        _chats.add(Chat("",
                    contact.id,
            "${contact.name} ${contact.surname}"))
    }
}