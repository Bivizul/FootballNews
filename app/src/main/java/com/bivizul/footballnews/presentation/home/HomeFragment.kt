package com.bivizul.footballnews.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentHomeBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.DEFAULT_IMAGE
import com.bivizul.footballnews.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(private val teamSelect:Int = 2) : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "SelectHome: $teamSelect")
        viewModel.getTeamInfo()
        viewModel.teamInfo.observe(viewLifecycleOwner) {
            Log.d(TAG, "it: ${it}")
            for (element in it) {
                if (element.id == teamSelect) {
                    Log.d(TAG, "element: $element")
                    with(binding){
                        if(element.icon.isNotEmpty()){
                            imgTeam.load(element.icon)
                        } else {
                            imgTeam.load(DEFAULT_IMAGE)
                        }
                        tvName.text = element.name
                        tvFullName.text = element.full_name
                        tvNickname.text = element.nickname
                        tvFounded.text = element.founded
                        tvWebsite.text = element.website
                        tvNameVenue.text = element.venue_name
                        tvCapacity.text = element.capacity
                        tvManager.text = element.manager
                        tvChairman.text = element.chairman
                    }
                }
            }
        }
    }
}