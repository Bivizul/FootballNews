package com.bivizul.footballnews.data.network

import com.bivizul.footballnews.domain.models.TeamInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/5footballnews/json/team_list7.json")
    suspend fun getTeamList() : Response<List<TeamInfo>>

}