package com.alanturing.cpifp.whatsappclone.register.data

import com.alanturing.cpifp.whatsappclone.core.network.WhatsAppCloneService
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val networkService: WhatsAppCloneService) {
    suspend fun register(phone: String): User? {
        val newUser = User(phone=phone)
        val response = networkService.register(newUser)

        return if (response.isSuccessful)
            response.body()
        else {
            null
        }
        /*val response = networkService.register(newUser)
        if (response.isSuccessful) {
            return response.body()!!
        }
        else {
            return null
        }*/
    }
}