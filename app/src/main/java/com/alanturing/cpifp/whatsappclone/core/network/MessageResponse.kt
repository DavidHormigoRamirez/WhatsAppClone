package com.alanturing.cpifp.whatsappclone.core.network


data class MessageResponse(
    val id: Long,
    val text: String,
    val timeSent: String,
    val sender: ContactResponse,
    val receiver: ContactResponse
)


