package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.adapters.MovieAdapter
import com.example.cinemaapp.models.Movie

class NowShowingFragment : Fragment(R.layout.fragment_now_showing) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_now_showing)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MovieAdapter(getNowShowingMovies()) { movie ->
            // Handle click event, e.g., navigate to movie details
            // val action = NowShowingFragmentDirections.actionNowShowingFragmentToFilmDetailsFragment(movie)
            // findNavController().navigate(action)
        }
    }

    private fun getNowShowingMovies(): List<Movie> {
        // Fetch or create a list of movies currently showing
        return listOf(
            Movie("Godzilla x Kong: The New Empire", "17:00", "https://example.com/godzilla_kong.jpg"),
            Movie("The Joker: Folie A Deux", "16:40", "https://example.com/joker.jpg"),
            Movie("Kung Fu Panda 4", "15:00", "https://example.com/kung_fu_panda.jpg")
        )
    }
}
