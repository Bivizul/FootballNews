package com.bivizul.footballnews.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.footballnews.data.network.ApiRepository
import com.bivizul.footballnews.domain.models.Player
import com.bivizul.footballnews.domain.models.Result
import com.bivizul.footballnews.domain.models.TeamInfo
import com.bivizul.footballnews.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel@Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _teamInfo = MutableLiveData<List<TeamInfo>>()
    val teamInfo: LiveData<List<TeamInfo>>
        get() = _teamInfo

    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>>
        get() = _players

    private val _results = MutableLiveData<List<Result>>()
    val results: LiveData<List<Result>>
        get() = _results

    fun getTeamInfo() {
        viewModelScope.launch {
            repository.getTeamList().let {
                if (it.isSuccessful) {
                    _teamInfo.postValue(it.body())
                } else {
                    Log.d(TAG, "Failed to load table: ${it.errorBody()}")
                }
            }
        }
    }

    fun setPlayers(playersList:List<Player>){
        _players.postValue(playersList)
    }

    fun setResults(resultsList:List<Result>){
        _results.postValue(resultsList)
    }
}