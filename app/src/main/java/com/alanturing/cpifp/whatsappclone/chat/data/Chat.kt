package com.alanturing.cpifp.whatsappclone.chat.data

data class Chat(
    val image: String,
    val to:Long, // Identificador de la persona a la que se le escribe
    val toName: String // Nombre de la persona a la que se le escribe
)
