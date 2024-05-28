package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager


class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve film details from arguments and display them on the fragment
        val filmTitle = arguments?.getString("title")
        // Display film details...
    }
}
