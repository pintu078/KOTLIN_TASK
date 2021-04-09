package com.pintu.kotlin_task.network

import com.pintu.kotlin_task.model.recycler.User
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    @GET("users")
    fun getUsers(): Call<MutableList<User>>

}