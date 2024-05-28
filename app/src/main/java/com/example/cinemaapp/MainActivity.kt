package com.example.cinemaapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_films -> {
                    loadFragment(FilmsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_account -> {
                    loadFragment(AccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_map -> {
                    loadFragment(MapFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        // Set default selected item
        bottomNavigationView.selectedItemId = R.id.navigation_films
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
