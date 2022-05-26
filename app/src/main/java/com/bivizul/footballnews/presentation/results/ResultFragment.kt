package com.bivizul.footballnews.presentation.results

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentPlayersBinding
import com.bivizul.footballnews.databinding.FragmentResultBinding
import com.bivizul.footballnews.presentation.players.PlayerAdapter
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment(private val teamSelect:Int) : Fragment() {

    private lateinit var viewModel: TeamViewModel

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding ?: throw RuntimeException("FragmentResultBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(Constants.TAG, "SelectResult: $teamSelect")

        val adapter = ResultAdapter()
        binding.rvListResults.adapter = adapter

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.getTeamInfo()
        viewModel.teamInfo.observe(viewLifecycleOwner) {
            Log.d(Constants.TAG, "it: ${it}")
            for (element in it) {
                if (element.id == teamSelect) {
                    viewModel.setResults(element.results)

                    Log.d(Constants.TAG, "element: $element")
                    Log.d(Constants.TAG, "elementResults: ${element.results}")


                } else {
                    Log.d(Constants.TAG, "else")
                }
            }
        }
        viewModel.results.observe(viewLifecycleOwner) {
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