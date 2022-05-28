package com.bivizul.footballnews.domain.models

import androidx.annotation.Keep

@Keep
data class TeamInfo(
    val id: Int,
    val name: String,
    val full_name: String,
    val nickname: String,
    val founded: String,
    val website: String,
    val venue_name: String,
    val capacity: String,
    val manager: String,
    val chairman: String,
    val icon: String,
    val players: List<Player>,
    val results: List<Result>
)