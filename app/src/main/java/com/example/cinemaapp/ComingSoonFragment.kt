package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.adapters.MovieAdapter
import com.example.cinemaapp.models.Movie

class ComingSoonFragment : Fragment(R.layout.fragment_coming_soon) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_coming_soon)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MovieAdapter(getComingSoonMovies()) { movie ->
            // Handle click event, e.g., navigate to movie details
            // val action = ComingSoonFragmentDirections.actionComingSoonFragmentToFilmDetailsFragment(movie)
            // findNavController().navigate(action)
        }
    }

    private fun getComingSoonMovies(): List<Movie> {
        // Fetch or create a list of upcoming movies
        return listOf(
            // Example data
            Movie("Avatar 2", "Release Date: 18 Dec 2024", "https://example.com/avatar2.jpg"),
            Movie("Spider-Man: No Way Home", "Release Date: 17 Dec 2024", "https://example.com/spiderman.jpg")
        )
    }
}
