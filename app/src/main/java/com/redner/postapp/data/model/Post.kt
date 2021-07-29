package com.redner.postapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val userId : String,
    val id : String,
    val title : String,
    val body : String
): Parcelable
