package com.example.cinemaapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String = "",
    val time: String = "",
    val imageUrl: String = ""
) : Parcelable
