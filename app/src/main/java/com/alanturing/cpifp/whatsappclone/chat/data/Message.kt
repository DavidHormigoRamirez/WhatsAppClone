package com.alanturing.cpifp.whatsappclone.chat.data

import kotlinx.datetime.Instant

data class Message(
    val id: Long,
    val text: String,
    val incoming: Boolean,
    val instant: Instant

)
