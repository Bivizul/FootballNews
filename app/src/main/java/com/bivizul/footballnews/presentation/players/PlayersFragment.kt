package com.bivizul.footballnews.presentation.players

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentHomeBinding
import com.bivizul.footballnews.databinding.FragmentPlayersBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment(private val teamSelect: String) : Fragment() {

    private lateinit var viewModel: TeamViewModel

    private var _binding: FragmentPlayersBinding? = null
    private val binding: FragmentPlayersBinding
        get() = _binding ?: throw RuntimeException("FragmentPlayersBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPlayersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(Constants.TAG, "SelectPlayers: $teamSelect")

        val adapter = PlayerAdapter()
        binding.rvListPlayers.adapter = adapter

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.getTeamInfo()
        viewModel.teamInfo.observe(viewLifecycleOwner) {
            Log.d(Constants.TAG, "it: ${it}")
            for (element in it) {
                if (element.name == teamSelect) {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val TEAM_SELECT = "TEAM_SELECT"

    }
}