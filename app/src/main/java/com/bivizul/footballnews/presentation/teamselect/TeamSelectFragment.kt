package com.bivizul.footballnews.presentation.teamselect

import android.os.Bundle
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

        val listName = listOf<String>()


        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        with(binding) {
//            rvSearch.adapter = adapter

            viewModel.getTeamInfo()
            viewModel.teamInfo.observe(viewLifecycleOwner) {
                teamSelectionAdapter.submitList(it)

            }

            btNext.setOnClickListener {
                findNavController().navigate(R.id.action_teamSelectFragment_to_mainFragment)
            }
        }

    }

    private fun setupRecycleView() {
        with(binding.rvSearch) {
            teamSelectionAdapter = TeamSelectionAdapter()
            adapter = teamSelectionAdapter
        }
        setupClickListener()
    }

    // короткий клик
    private fun setupClickListener() {
        teamSelectionAdapter.onTeamItemClickListener = {
            findNavController().navigate(R.id.action_teamSelectFragment_to_mainFragment,
            bundleOf(MainFragment.TEAM_SELECT to it.name))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}