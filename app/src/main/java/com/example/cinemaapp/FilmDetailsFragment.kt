package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cinemaapp.databinding.FragmentFilmDetailsBinding
import com.example.cinemaapp.models.Movie

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie? = arguments?.getParcelable("movie")

        movie?.let {
            binding.filmTitle.text = it.title
            binding.filmTime.text = it.time
            binding.filmDescription.text = it.description
            Glide.with(binding.filmImage.context)
                .load(it.imageUrl)
                .into(binding.filmImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
