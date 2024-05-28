package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class FilmsFragment : Fragment(R.layout.fragment_films) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)

        val adapter = object : FragmentPagerAdapter(childFragmentManager) {
            override fun getCount(): Int = 3 // Now Showing, Coming Soon, My Bookings

            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> NowShowingFragment()
                    1 -> ComingSoonFragment()
                    else -> MyBookingsFragment()
                }
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return when (position) {
                    0 -> "Now Showing"
                    1 -> "Coming Soon"
                    else -> "My Bookings"
                }
            }
        }

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
    }
}
