package com.rancic.development.demo.app.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AddHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
       // builder.addHeader("Content-Type", "application/json")
        return chain.proceed(builder.build())
    }
}