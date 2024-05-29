package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.adapters.MovieAdapter
import com.example.cinemaapp.models.Movie
import com.google.firebase.firestore.FirebaseFirestore

class FilmsFragment : Fragment(R.layout.fragment_films) {

    private lateinit var recyclerView: RecyclerView
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_films)
        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchMovies()
    }

    private fun fetchMovies() {
        firestore.collection("films")
            .get()
            .addOnSuccessListener { result ->
                val movies = result.map { document ->
                    document.toObject(Movie::class.java)
                }
                recyclerView.adapter = MovieAdapter(movies) { movie ->
                    // Navigate to FilmDetailsFragment
                    val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
                    val filmDetailsFragment = FilmDetailsFragment().apply {
                        val bundle = Bundle().apply {
                            putParcelable("movie", movie)
                        }
                        arguments = bundle
                    }
                    fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, filmDetailsFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }
}
