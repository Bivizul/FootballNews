package com.bivizul.footballnews.presentation.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentSplashBinding
import com.bivizul.footballnews.presentation.main.MainFragment
import com.bivizul.footballnews.utils.Constants
import com.bivizul.footballnews.utils.Constants.APP_PREFERENCES_ID
import com.bivizul.footballnews.utils.Constants.PREF_ZERO
import com.bivizul.footballnews.utils.Constants.SPLASH_MILLIS
import com.bivizul.footballnews.utils.Constants.TAG

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() = _binding ?: throw RuntimeException("FragmentSplashBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences =
            requireActivity().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val pref = preferences.getInt(APP_PREFERENCES_ID,PREF_ZERO)
        binding.imageView.load("http://65.109.10.118/5footballnews/images/ball.jpg")


        Log.d(TAG,"preferencesSplash: $pref")

        if (pref == PREF_ZERO){
            view.postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_teamSelectFragment)
            }, SPLASH_MILLIS)
        } else {
            view.postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment,
                    bundleOf(MainFragment.TEAM_SELECT to pref))
            }, SPLASH_MILLIS)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}