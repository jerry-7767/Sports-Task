package com.rishi.sportstask.Retrofit

import com.rishi.sportstask.Model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("nzin01312019187360.json")
    fun getIndNz(
    ): Call<ApiResponse>

    @GET("sapk01222019186652.json")
    fun getSaPak(
    ): Call<ApiResponse>
}