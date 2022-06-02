package com.bivizul.footballnews.presentation.splash

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentSplashBinding
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.PREF_TEAM_ZERO
import com.bivizul.footballnews.utils.Constants.SPLASH_IMAGE
import com.bivizul.footballnews.utils.Constants.SPLASH_MILLIS
import com.bivizul.footballnews.utils.Constants.TAG
import com.bivizul.footballnews.utils.Constants.TEAM_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val preferences by lazy {
        requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    }
    private val teamSelect by lazy {
        preferences.getInt(TEAM_ID, PREF_TEAM_ZERO)
    }
    private val darkThemeCheck by lazy {
        preferences.getBoolean(Constants.DARK_THEME_CHECK, Constants.PREF_DARK_THEME)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "Splash onViewCreated")

        val locale = getCurrentLocale(requireContext())

        if (darkThemeCheck) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding.imageView.load(SPLASH_IMAGE)

        viewModel.getSplash(locale!!)
        viewModel.splash.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(SPLASH_MILLIS)
                if (it.url == "no") {
                    if (teamSelect == PREF_TEAM_ZERO) {
                        findNavController().navigate(R.id.action_splashFragment_to_teamSelectFragment)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }
                } else {
                    setUrl(it.url)
                }
            }
        }
        Log.d(TAG, "teamSelectSplash: $teamSelect")
    }

    private fun setUrl(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireActivity(), Uri.parse(url))
    }

    private fun getCurrentLocale(context: Context): Locale? =
        context.resources.configuration.locales[0]

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Splash onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Splash onDestroy")
    }
}
