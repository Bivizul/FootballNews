package com.bivizul.footballnews.presentation.players

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentPlayersBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment() : Fragment(R.layout.fragment_players) {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentPlayersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences =
            requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val teamSelect = preferences.getInt(Constants.TEAM_ID, Constants.PREF_TEAM_ZERO)

        val adapter = PlayerAdapter()
        binding.rvListPlayers.adapter = adapter

        viewModel.teamInfo.observe(viewLifecycleOwner) {
            for (element in it) {
                if (element.id == teamSelect) {
                    viewModel.setPlayers(element.players)
                }
            }
            viewModel.players.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}