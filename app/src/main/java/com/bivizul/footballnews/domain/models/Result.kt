package com.bivizul.footballnews.domain.models

import androidx.annotation.Keep

@Keep
data class Result(
    val date: String,
    val emblem: String,
    val name_team: String,
    val score: String,
    val symbol: String,
    val type: String
)