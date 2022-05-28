package com.bivizul.footballnews.presentation.players

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentHomeBinding
import com.bivizul.footballnews.databinding.FragmentPlayersBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment(private val teamSelect: Int) : Fragment(R.layout.fragment_players) {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentPlayersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(Constants.TAG, "SelectPlayers: $teamSelect")

        val adapter = PlayerAdapter()
        binding.rvListPlayers.adapter = adapter

        viewModel.getTeamInfo()
        viewModel.teamInfo.observe(viewLifecycleOwner) {
            Log.d(Constants.TAG, "it: ${it}")
            for (element in it) {
                if (element.id == teamSelect) {
                    viewModel.setPlayers(element.players)

                    Log.d(Constants.TAG, "element: $element")
                    Log.d(Constants.TAG, "elementPlayers: ${element.players}")


                } else {
                    Log.d(Constants.TAG, "else")
                }
            }
        }
        viewModel.players.observe(viewLifecycleOwner) {
            Log.d(Constants.TAG, "itPlayers: $it")
            adapter.submitList(it)
        }
    }
}