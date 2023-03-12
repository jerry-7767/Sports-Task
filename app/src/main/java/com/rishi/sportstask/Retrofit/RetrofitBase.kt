package com.rishi.sportstask.Retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBase {
    fun getClient(): Retrofit? {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClientBuilder = OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
        httpClientBuilder.readTimeout(Constant.TimeOut.SOCKET_TIME_OUT.toLong(), TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(
            Constant.TimeOut.CONNECTION_TIME_OUT.toLong(),
            TimeUnit.SECONDS
        )
        val okHttpClient = httpClientBuilder.build()
        return Retrofit.Builder()
            .baseUrl(Constant.UrlPath.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}