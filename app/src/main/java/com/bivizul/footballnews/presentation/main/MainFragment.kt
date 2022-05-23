package com.bivizul.footballnews.presentation.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.bivizul.footballnews.R
import com.bivizul.footballnews.databinding.FragmentMainBinding
import com.bivizul.footballnews.presentation.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            imgBtBurger.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }

        }

        initial()

    }

    private fun initial() {
//        binding.viewPager.adapter = PagerAdapter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}