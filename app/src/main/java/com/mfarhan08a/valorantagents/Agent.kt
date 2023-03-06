package com.mfarhan08a.valorantagents

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val name: String,
    val role: String,
    val biography: String,
    val icon: String,
    val art: Int
) : Parcelable
