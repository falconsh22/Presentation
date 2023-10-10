package com.shahin.presentation.network

import com.google.gson.Gson
import com.google.gson.JsonParseException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

open class NetworkResponseWrapper {
    inline fun <T> networkResponseOf(service: () -> Response<T>): NetworkResponse<T> {
        return try {
            val response = service()
            if (response.isSuccessful) {
                NetworkResponse.Success(response.body())
            } else {
                try {
                    val gson = Gson()
                    val typedValue = gson.fromJson(
                        response.errorBody()?.string(),
                        Error::class.java
                    )
                    NetworkResponse.GenericError(typedValue)
                } catch (e: JsonParseException) {
                    NetworkResponse.GenericError(Error(response.message()))
                } catch (e: Exception) {
                    NetworkResponse.GenericError(Error(response.message()))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            NetworkResponse.NetworkError
        }
    }
}
