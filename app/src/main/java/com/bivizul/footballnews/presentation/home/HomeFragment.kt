package com.bivizul.footballnews.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentHomeBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.DEFAULT_IMAGE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences =
            requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val teamSelect = preferences.getInt(Constants.TEAM_ID, Constants.PREF_TEAM_ZERO)

        viewModel.teamInfo.observe(viewLifecycleOwner) {
            for (element in it) {
                if (element.id == teamSelect) {
                    with(binding) {
                        if (element.icon.isNotEmpty()) {
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