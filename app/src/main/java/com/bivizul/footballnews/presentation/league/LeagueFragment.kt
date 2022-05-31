package com.bivizul.footballnews.presentation.league

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentLeagueBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueFragment() : Fragment(R.layout.fragment_league) {

    private val viewModel by viewModels<LeagueViewModel>()
    private val binding by viewBinding(FragmentLeagueBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.league.observe(viewLifecycleOwner) {
            with(binding) {
                tvD1CG.text = it.D1CG
                tvD2CG.text = it.D2CG
                tvD3CG.text = it.D3CG
                tvD4CG.text = it.D4CG
                tvD5CG.text = it.D5CG
                tvD6CG.text = it.D6CG
                tvG10TS.text = it.G10TS
                tvG1TS.text = it.G1TS
                tvG2TS.text = it.G2TS
                tvG3TS.text = it.G3TS
                tvG4TS.text = it.G4TS
                tvG5TS.text = it.G5TS
                tvG6TS.text = it.G6TS
                tvG7TS.text = it.G7TS
                tvG8TS.text = it.G8TS
                tvG9TS.text = it.G9TS
                tvL1CG.text = it.L1CG
                tvL2CG.text = it.L2CG
                tvL3CG.text = it.L3CG
                tvL4CG.text = it.L4CG
                tvL5CG.text = it.L5CG
                tvL6CG.text = it.L6CG
                tvLeftTeam.text = it.LeftTeam
                tvName10TS.text = it.Name10TS
                tvName1TS.text = it.Name1TS
                tvName2TS.text = it.Name2TS
                tvName3TS.text = it.Name3TS
                tvName4TS.text = it.Name4TS
                tvName5TS.text = it.Name5TS
                tvName6TS.text = it.Name6TS
                tvName7TS.text = it.Name7TS
                tvName8TS.text = it.Name8TS
                tvName9TS.text = it.Name9TS
                tvP10TS.text = it.P10TS
                tvP1CG.text = it.P1CG
                tvP1TS.text = it.P1TS
                tvP2CG.text = it.P2CG
                tvP2TS.text = it.P2TS
                tvP3CG.text = it.P3CG
                tvP3TS.text = it.P3TS
                tvP4CG.text = it.P4CG
                tvP4TS.text = it.P4TS
                tvP5CG.text = it.P5CG
                tvP5TS.text = it.P5TS
                tvP6CG.text = it.P6CG
                tvP6TS.text = it.P6TS
                tvP7TS.text = it.P7TS
                tvP8TS.text = it.P8TS
                tvP9TS.text = it.P9TS
                tvPP1CG.text = it.PP1CG
                tvPP2CG.text = it.PP2CG
                tvPP3CG.text = it.PP3CG
                tvPP4CG.text = it.PP4CG
                tvPP5CG.text = it.PP5CG
                tvPP6CG.text = it.PP6CG
                tvPlusMinus1CG.text = it.PlusMinus1CG
                tvPlusMinus2CG.text = it.PlusMinus2CG
                tvPlusMinus3CG.text = it.PlusMinus3CG
                tvPlusMinus4CG.text = it.PlusMinus4CG
                tvPlusMinus5CG.text = it.PlusMinus5CG
                tvPlusMinus6CG.text = it.PlusMinus6CG
                tvRightTeam.text = it.RightTeam
                tvScore.text = it.Score
                tvTeam10TS.text = it.Team10TS
                tvTeam1CG.text = it.Team1CG
                tvTeam1TS.text = it.Team1TS
                tvTeam2CG.text = it.Team2CG
                tvTeam2TS.text = it.Team2TS
                tvTeam3CG.text = it.Team3CG
                tvTeam3TS.text = it.Team3TS
                tvTeam4CG.text = it.Team4CG
                tvTeam4TS.text = it.Team4TS
                tvTeam5CG.text = it.Team5CG
                tvTeam5TS.text = it.Team5TS
                tvTeam6CG.text = it.Team6CG
                tvTeam6TS.text = it.Team6TS
                tvTeam7TS.text = it.Team7TS
                tvTeam8TS.text = it.Team8TS
                tvTeam9TS.text = it.Team9TS
                tvW1CG.text = it.W1CG
                tvW2CG.text = it.W2CG
                tvW3CG.text = it.W3CG
                tvW4CG.text = it.W4CG
                tvW5CG.text = it.W5CG
                tvW6CG.text = it.W6CG
                imgLogoLeft.load(it.imgLogoLeft)
                imgLogoRight.load(it.imgLogoRight)
                imgTeam1CG.load(it.imgTeam1CG)
                imgTeam2CG.load(it.imgTeam2CG)
                imgTeam3CG.load(it.imgTeam3CG)
                imgTeam4CG.load(it.imgTeam4CG)
                imgTeam5CG.load(it.imgTeam5CG)
                imgTeam6CG.load(it.imgTeam6CG)
            }
        }
    }
}