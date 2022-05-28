package com.bivizul.footballnews.presentation.results

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentResultBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment(private val teamSelect: Int) : Fragment(R.layout.fragment_result) {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentResultBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(Constants.TAG, "SelectResult: $teamSelect")

        val adapter = ResultAdapter()
        binding.rvListResults.adapter = adapter

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

    companion object {

        const val TEAM_SELECT = "TEAM_SELECT"

    }

}