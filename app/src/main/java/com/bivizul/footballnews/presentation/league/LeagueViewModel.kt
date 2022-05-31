package com.bivizul.footballnews.presentation.league

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.footballnews.data.network.ApiRepository
import com.bivizul.footballnews.domain.models.League
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _league = MutableLiveData<League>()
    val league: LiveData<League>
        get() = _league

    init {
        getLeague()
    }

    private fun getLeague() {
        viewModelScope.launch {
            repository.getLeague().let {
                if (it.isSuccessful) {
                    _league.postValue(it.body())
                } else {
                    Log.d(Constants.TAG, "Failed to load table: ${it.errorBody()}")
                }
            }
        }
    }
}