package com.alanturing.cpifp.whatsappclone.chat.data

data class Message(
    val to:Long,
    val incoming:Boolean,
    val text:String
)
