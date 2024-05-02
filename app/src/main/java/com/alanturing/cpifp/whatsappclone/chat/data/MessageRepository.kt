package com.alanturing.cpifp.whatsappclone.chat.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepository @Inject constructor(private val messageNetworkRepository: MessageNetworkRepository) {
    private val _message: MutableList<Message> = mutableListOf()
    val message: List<Message>
        get() = _message.toList()

    fun addMessage(text:String,incoming:Boolean,sender:Long) {
        val message = Message(
            sender,
            text,
            incoming,
            kotlinx.datetime.Clock.System.now()
        )
        _message.add(message)
    }

    fun getMessage(sender:Long): List<Message> {
        val response = messageNetworkRepository.getAllMessages()
        return _message.filter {it.id == sender}
    }
}