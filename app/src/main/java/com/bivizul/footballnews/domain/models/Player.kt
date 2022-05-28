package com.bivizul.footballnews.domain.models

import androidx.annotation.Keep

@Keep
data class Player(
    val country: String,
    val flag: String,
    val name: String,
    val photo: String,
    val type: String
)