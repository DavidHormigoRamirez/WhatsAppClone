package com.alanturing.cpifp.whatsappclone.chat.data

class MessageRepository {

    private val _messages = mutableListOf<Message>()
    val message: List<Message>
        get() = _messages.toList()

    init {
        _messages.add(Message(1,true,"Estas por ahi?"))
        _messages.add(Message(1,true,"Me puedes llamar?"))
        _messages.add(Message(1,false,"Te llamo en un rato"))
        _messages.add(Message(2,false,"¿Nos vamos de picnic?"))
        _messages.add(Message(15,false,"Llamame"))
        _messages.add(Message(15,false,"llamame"))
        _messages.add(Message(15,false,"bebebe bebebeb"))
        _messages.add(Message(15,false,"llamame, llamame"))
    }
}