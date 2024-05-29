package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentSeatsBinding
import com.example.cinemaapp.models.Movie

class SeatsFragment : Fragment(R.layout.fragment_seats) {

    private var _binding: FragmentSeatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie? = arguments?.getParcelable("movie")
        val selectedDate = arguments?.getString("selectedDate")
        val adultTickets = arguments?.getInt("adultTickets") ?: 0
        val seniorTickets = arguments?.getInt("seniorTickets") ?: 0
        val studentTickets = arguments?.getInt("studentTickets") ?: 0
        val childTickets = arguments?.getInt("childTickets") ?: 0

        movie?.let {
            binding.filmTitle.text = it.title
            binding.selectedDate.text = selectedDate
            binding.selectedTickets.text = "Adult: $adultTickets, Senior: $seniorTickets, Student: $studentTickets, Child: $childTickets"
        }

        binding.buttonContinue.setOnClickListener {
            // Handle continue button click to proceed to the next step
        }

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
