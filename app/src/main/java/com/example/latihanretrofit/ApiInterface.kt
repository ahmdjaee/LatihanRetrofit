package com.example.latihanretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/albums")
    fun getData(): Call<Albums>
}