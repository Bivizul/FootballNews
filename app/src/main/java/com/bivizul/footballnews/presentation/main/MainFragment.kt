package com.bivizul.footballnews.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.internal.view.SupportMenu
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentMainBinding
import com.bivizul.footballnews.presentation.adapters.PagerAdapter
import com.bivizul.footballnews.presentation.home.HomeFragment
import com.bivizul.footballnews.presentation.viewmodels.TeamViewModel
import com.bivizul.footballnews.utils.Constants
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: TeamViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

//    init {
//        // Указываем тулбар по умолчанию
////        SupportMenu(binding.toolbar)
//
//        val toggle = ActionBarDrawerToggle(
//            requireActivity(),
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.open,
//            R.string.close
//        )
//        // открытие по нажатию
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//        // передача событий из NavigationView по нажатию в onNavigationItemSelected
//        binding.navView.setNavigationItemSelectedListener(this)
////        tvAccount = binding.navView.getHeaderView(0).findViewById(R.id.tvAccountEmail)
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamSelect = arguments?.getInt(HomeFragment.TEAM_SELECT)

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.getTeamInfo()
        viewModel.teamInfo.observe(viewLifecycleOwner) {
            for (element in it) {
                if (element.id == teamSelect) {
                    binding.tvYouTeam.text = element.name
                }
            }

            Log.d(Constants.TAG, "teamSelectMain: $teamSelect")
            with(binding) {

                navView.setNavigationItemSelectedListener(this@MainFragment)

                imgBtBurger.setOnClickListener {
                    drawerLayout.openDrawer(GravityCompat.START)
                }

                viewPager.adapter = PagerAdapter(requireActivity(), teamSelect!!)
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.setText("Home")
                        1 -> tab.setText("Result")
                        2 -> tab.setText("League")
                        else -> tab.setText("Players")
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
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.selectTeam -> {
                Toast.makeText(requireContext(), "selectTeam", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_mainFragment_to_teamSelectFragment)
            }
            R.id.wallpaper -> {
                Toast.makeText(requireContext(), "Wallpaper", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView = view?.findViewById(R.id.selectTeam) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        const val TEAM_SELECT = "TEAM_SELECT"

    }
}
