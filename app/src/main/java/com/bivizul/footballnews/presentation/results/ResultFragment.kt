package com.bivizul.footballnews.presentation.results

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentResultBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment() : Fragment(R.layout.fragment_result) {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentResultBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences =
            requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val teamSelect = preferences.getInt(Constants.TEAM_ID, Constants.PREF_TEAM_ZERO)

        val adapter = ResultAdapter()
        binding.rvListResults.adapter = adapter

        viewModel.teamInfo.observe(viewLifecycleOwner) {
            for (element in it) {
                if (element.id == teamSelect) {
                    viewModel.setResults(element.results)
                }
            }
        }
        viewModel.results.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}