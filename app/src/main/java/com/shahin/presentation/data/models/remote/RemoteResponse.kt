package com.shahin.presentation.data.models.remote

import com.google.gson.annotations.SerializedName

data class RemoteResponse<T>(

    @SerializedName("total")
    val total: Int,

    @SerializedName("data")
    val data: List<T>
)
