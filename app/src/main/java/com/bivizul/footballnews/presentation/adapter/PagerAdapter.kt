package com.bivizul.footballnews.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bivizul.footballnews.presentation.home.HomeFragment
import com.bivizul.footballnews.presentation.league.LeagueFragment
import com.bivizul.footballnews.presentation.players.PlayersFragment
import com.bivizul.footballnews.presentation.results.ResultFragment
import com.bivizul.footballnews.utils.Constants.COUNTER_VIEWPAGER

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return COUNTER_VIEWPAGER
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> ResultFragment()
            2 ->LeagueFragment()
            else -> PlayersFragment()
        }
    }
}