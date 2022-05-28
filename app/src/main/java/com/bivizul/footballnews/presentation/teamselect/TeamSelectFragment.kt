package com.bivizul.footballnews.presentation.teamselect

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentTeamSelectBinding
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.APP_PREFERENCES
import com.bivizul.footballnews.utils.Constants.TEAM_ID
import com.bivizul.footballnews.utils.Constants.TEAM_SELECT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSelectFragment : Fragment(R.layout.fragment_team_select) {

    private val viewModel by viewModels<TeamViewModel>()
    private lateinit var teamSelectionAdapter: TeamSelectionAdapter
    private val binding by viewBinding(FragmentTeamSelectBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()

        viewModel.getTeamInfo()
        viewModel.teamInfo.observe(viewLifecycleOwner) {
            teamSelectionAdapter.submitList(it)
        }

    }

    private fun setupRecycleView() {
        with(binding.rvSearch) {
            teamSelectionAdapter = TeamSelectionAdapter()
            adapter = teamSelectionAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        teamSelectionAdapter.onTeamItemClickListener = {

            val preferences =
                requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

            preferences?.edit()?.putInt(TEAM_ID, it.id)?.apply()
            Log.d(Constants.TAG, "preferencesTeamSelect: ${
                preferences?.edit()?.putInt(TEAM_ID, it.id)?.apply()
            }")

            findNavController().navigate(R.id.action_teamSelectFragment_to_mainFragment,
                bundleOf(TEAM_SELECT to it.id))

        }
    }
}