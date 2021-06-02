package com.dendi.filmscatalogs.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "listentities")
data class ListEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "images")
    var images: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean,

    @ColumnInfo(name = "type")
    var type: String,
) : Parcelable