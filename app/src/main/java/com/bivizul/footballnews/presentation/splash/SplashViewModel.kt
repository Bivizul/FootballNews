package com.bivizul.footballnews.presentation.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.footballnews.data.network.ApiRepository
import com.bivizul.footballnews.domain.models.Answer
import com.bivizul.footballnews.domain.models.League
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _splash = MutableLiveData<Answer>()
    val splash: LiveData<Answer>
        get() = _splash

    fun getSplash(send: Locale) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSplash(send).let {
                if (it.isSuccessful) {
                    _splash.postValue(it.body())
                } else {
                    Log.d(Constants.TAG, "Failed to load table: ${it.errorBody()}")
                }
            }
        }
    }
}