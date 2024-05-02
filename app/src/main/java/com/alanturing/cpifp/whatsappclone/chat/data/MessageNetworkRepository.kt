package com.alanturing.cpifp.whatsappclone.chat.data

import com.alanturing.cpifp.whatsappclone.core.network.MessageResponse
import com.alanturing.cpifp.whatsappclone.core.network.WhatsAppCloneService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageNetworkRepository @Inject constructor(
    private val networkService: WhatsAppCloneService
) {
    fun getAllMessages(): List<MessageResponse> {
        return runBlocking {
            return@runBlocking withContext(Dispatchers.IO) {
                return@withContext networkService.getAllMessages(1002)
            }
        }
    }
}