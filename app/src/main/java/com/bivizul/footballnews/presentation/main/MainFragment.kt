package com.bivizul.footballnews.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentMainBinding
import com.bivizul.footballnews.presentation.adapters.PagerAdapter
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.APP_PREFERENCES
import com.bivizul.footballnews.utils.Constants.DARK_THEME_CHECK
import com.bivizul.footballnews.utils.Constants.NAV_HEADER
import com.bivizul.footballnews.utils.Constants.NOTIFICATION_CHECK
import com.bivizul.footballnews.utils.Constants.PREF_DARK_THEME
import com.bivizul.footballnews.utils.Constants.PREF_NOTIFICATION
import com.bivizul.footballnews.utils.Constants.TEAM_ID
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main),
    NavigationView.OnNavigationItemSelectedListener {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val preferences by lazy {
        requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }
    private val teamSelect by lazy { preferences.getInt(TEAM_ID, Constants.PREF_TEAM_ZERO) }
    private val darkThemeCheck by lazy {
        preferences.getBoolean(DARK_THEME_CHECK, PREF_DARK_THEME)
    }
    private val notificationCheck by lazy {
        preferences.getBoolean(NOTIFICATION_CHECK, PREF_NOTIFICATION)
    }
    private val switchDark by lazy {
        requireActivity().findViewById<SwitchCompat>(R.id.switch_dark_theme)
    }
    private val notifications by lazy {
        requireActivity().findViewById<SwitchCompat>(R.id.switch_notifications)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView = binding.navView
        val header = navView.getHeaderView(0)
        val img = header.findViewById<ImageView>(R.id.nav_header)
        img.load(NAV_HEADER)

        viewModel.teamInfo.observe(viewLifecycleOwner) {
            for (element in it) {
                if (element.id == teamSelect) {
                    binding.tvYouTeam.text = element.name
                    binding.imgTeamSelect.load(element.icon)
                }
            }
        }

        with(binding) {
            navView.setNavigationItemSelectedListener(this@MainFragment)
            imgBtBurger.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
                switchDark.isChecked = darkThemeCheck
                notifications.isChecked = notificationCheck
            }
            viewPager.adapter = PagerAdapter(requireActivity())
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Home"
                    1 -> tab.text = "Result"
                    2 -> tab.text = "League"
                    else -> tab.text = "Players"
                }
            }.attach()

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.alpha = 1F
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.alpha = 0.1F
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.selectTeam -> {
                findNavController().navigate(R.id.action_mainFragment_to_teamSelectFragment)
            }
            R.id.swDarkTheme -> {
                if (switchDark.isChecked) {
                    switchDark.isChecked = false
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                    preferences?.edit()?.putBoolean(DARK_THEME_CHECK, false)?.apply()
                } else {
                    switchDark.isChecked = true
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                    preferences?.edit()?.putBoolean(DARK_THEME_CHECK, true)?.apply()
                }
            }
            R.id.swNotifications -> {
                if (notifications.isChecked) {
                    notifications.isChecked = false
                    preferences?.edit()?.putBoolean(NOTIFICATION_CHECK, false)?.apply()
                } else {
                    notifications.isChecked = true
                    preferences?.edit()?.putBoolean(NOTIFICATION_CHECK, true)?.apply()
                }
            }
            R.id.exit -> {
                exitProcess(0)
            }
            else -> {}
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
