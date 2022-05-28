package com.bivizul.footballnews.presentation.splash

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentSplashBinding
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.TEAM_ID
import com.bivizul.footballnews.utils.Constants.PREF_ZERO
import com.bivizul.footballnews.utils.Constants.SPLASH_IMAGE
import com.bivizul.footballnews.utils.Constants.SPLASH_MILLIS
import com.bivizul.footballnews.utils.Constants.TAG
import com.bivizul.footballnews.utils.Constants.TEAM_SELECT
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()
    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locale = getCurrentLocale(requireContext())
        Log.d(TAG, "locale: $locale")

        val preferences =
            requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val pref = preferences.getInt(TEAM_ID, PREF_ZERO)

        binding.imageView.load(SPLASH_IMAGE)

        viewModel.getSplash(locale!!)
        viewModel.splash.observe(viewLifecycleOwner) {
            Log.d(TAG, "itSplash: $it")
//            if(it.url == "no"){
                if (pref == PREF_ZERO) {
                    view.postDelayed({
                        findNavController().navigate(R.id.action_splashFragment_to_teamSelectFragment)
                    }, SPLASH_MILLIS)
                } else {
                    view.postDelayed({
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment,
                            bundleOf(TEAM_SELECT to pref))
                    }, SPLASH_MILLIS)
                }
//            } else {
//                setUrl(it.url)
//            }
        }
        Log.d(TAG, "preferencesSplash: $pref")
    }

//    private  fun setUrl(url:String){
//        val builder = CustomTabsIntent.Builder()
//        val customTabsIntent = builder.build()
//        customTabsIntent.launchUrl(requireActivity(), Uri.parse(url))
//    }

    private fun getCurrentLocale(context: Context): Locale? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            context.resources.configuration.locale
        }
    }
}