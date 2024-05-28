package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmTitle = arguments?.getString("film_title")
        val filmTime = arguments?.getString("film_time")

        val titleTextView = view.findViewById<TextView>(R.id.film_title)
        val timeTextView = view.findViewById<TextView>(R.id.film_time)

        titleTextView.text = filmTitle ?: "No Title"
        timeTextView.text = filmTime ?: "No Time"
    }
}
