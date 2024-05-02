package com.alanturing.cpifp.whatsappclone.core.network

import com.alanturing.cpifp.whatsappclone.register.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WhatsAppCloneService {

    @GET("message")
    suspend fun getAllMessages(@Query("user") userId:Long): List<MessageResponse>

    @POST("user")
    suspend fun register(@Body user: User): Response<User>

}