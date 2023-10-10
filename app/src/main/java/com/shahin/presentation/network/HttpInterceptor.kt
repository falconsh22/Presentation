package com.shahin.presentation.network

import com.shahin.presentation.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class HttpInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.SECRET_KEY

        val request = chain.request()
        val requestBuilder = request.newBuilder()

        if (request.header("No-Authentication") == null) {
            requestBuilder.addHeader("access_token", token)
        } else {
            requestBuilder.removeHeader("No-Authentication")
        }

        if (request.header("No-Locality") == null) {
            val url = chain.request().url.newBuilder()
                .addQueryParameter("locale", Locale.getDefault().language)
                .build()
            requestBuilder.url(url)
        } else {
            requestBuilder.removeHeader("No-Locality")
        }
        val newRequest = requestBuilder.build()
        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }

}
