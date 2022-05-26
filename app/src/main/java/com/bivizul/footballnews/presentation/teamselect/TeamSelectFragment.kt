package com.bivizul.footballnews.presentation.teamselect

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentTeamSelectBinding
import com.bivizul.footballnews.presentation.main.MainFragment
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.APP_PREFERENCES
import com.bivizul.footballnews.utils.Constants.APP_PREFERENCES_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamSelectFragment : Fragment() {

    private lateinit var viewModel: TeamViewModel
    private lateinit var teamSelectionAdapter: TeamSelectionAdapter


    private var _binding: FragmentTeamSelectBinding? = null
    private val binding: FragmentTeamSelectBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTeamSelectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
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

            preferences?.edit()?.putInt(APP_PREFERENCES_ID, it.id)?.apply()
            Log.d(Constants.TAG, "preferencesTeamSelect: ${
                preferences?.edit()?.putInt(APP_PREFERENCES_ID, it.id)?.apply()
            }")

            findNavController().navigate(R.id.action_teamSelectFragment_to_mainFragment,
                bundleOf(MainFragment.TEAM_SELECT to it.id))

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}