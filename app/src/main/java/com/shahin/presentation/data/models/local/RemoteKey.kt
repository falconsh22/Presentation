package com.shahin.presentation.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    val label: String,
    val prevKey: Int?,
    val nextKey: Int?
)
