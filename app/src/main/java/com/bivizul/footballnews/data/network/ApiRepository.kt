package com.bivizul.footballnews.data.network

import java.util.*
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTeamList() = apiService.getTeamList()

    suspend fun getLeague() = apiService.getLeague()

    suspend fun getSplash(send: Locale) = apiService.getSplash(send)

}