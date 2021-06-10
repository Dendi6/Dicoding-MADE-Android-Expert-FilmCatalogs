package com.dendi.filmscatalogs.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    val images: String,
    val poster: String,
    val overview: String,
    val favorited: Boolean,
    val type: String,
) : Parcelable
