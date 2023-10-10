package com.shahin.presentation.data.models.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteErrorResponse(

    @SerializedName("error")
    val error: Error? = null
) : Parcelable

@Parcelize
data class Error(

    @SerializedName("code")
    val code: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("message")
    val message: String
) : Parcelable
