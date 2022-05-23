package com.bivizul.footballnews.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import coil.load
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentSplashBinding
import com.bivizul.footballnews.databinding.FragmentStartBinding
import com.bivizul.footballnews.presentation.start.StartViewModel

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

        binding.imageView.load("http://65.109.10.118/5footballnews/ball.jpg")

//        val back = activity?.findViewById<ImageView>(R.id.imgBackActivity)
//        back?.load("http://65.109.10.118/4rateguide/background.jpg")
        view.postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_startFragment)
        }, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}