package com.bivizul.footballnews.data.network

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getTeamList() = apiService.getTeamList()



}