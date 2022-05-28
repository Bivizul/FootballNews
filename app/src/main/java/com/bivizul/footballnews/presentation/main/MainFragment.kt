package com.bivizul.footballnews.presentation.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
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
import com.bivizul.footballnews.utils.Constants.NAV_HEADER
import com.bivizul.footballnews.utils.Constants.TEAM_SELECT
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main),
    NavigationView.OnNavigationItemSelectedListener {

    private val viewModel by viewModels<TeamViewModel>()
    private val binding by viewBinding(FragmentMainBinding::bind)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView = binding.navView
        val header = navView.getHeaderView(0)
        val img = header.findViewById<ImageView>(R.id.nav_header)
        img.load(NAV_HEADER)

        val teamSelect = arguments?.getInt(TEAM_SELECT)

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

        val preferences = requireActivity().getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )

        when (item.itemId) {
            R.id.selectTeam -> {
                findNavController().navigate(R.id.action_mainFragment_to_teamSelectFragment)
            }
            R.id.wallpaper -> {
                Toast.makeText(requireContext(), "Wallpaper", Toast.LENGTH_LONG).show()
            }
            R.id.swDarkTheme -> {

                val switchDark = item.actionView.findViewById<Switch>(R.id.switch_id)
//                switchDark.isChecked = true
                if (item.isChecked) {
                    switchDark.isChecked = false
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                    Toast.makeText(requireContext(), "unChecked", Toast.LENGTH_SHORT).show()
                } else {
                    switchDark.isChecked = true
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                    Toast.makeText(requireContext(), "Checked", Toast.LENGTH_SHORT).show()
                }
//                switch_id.setOnClickListener(View.OnClickListener {
////                    Toast.makeText(ApplicationProvider.getApplicationContext<Context>(),
////                        if (switch_id.isChecked()) "is checked!!!" else "not checked!!!",
////                        Toast.LENGTH_SHORT).show()
//                    if (item.isChecked) {
//                        Toast.makeText(requireContext(), "Checked", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(requireContext(), "unChecked", Toast.LENGTH_SHORT).show()
//                    }
//                })

//               ( item.actionView as Switch).toggle()
//                return true

//                (item.actionView as SwitchCompat).setOnCheckedChangeListener { buttonView, isChecked ->
//                    if (isChecked) {
//                        Toast.makeText(buttonView.context, "Checked", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(buttonView.context, "unChecked", Toast.LENGTH_SHORT).show()
//                    }
//                }
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

}
