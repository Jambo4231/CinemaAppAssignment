package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentSeatsBinding
import com.example.cinemaapp.models.Movie

class SeatsFragment : Fragment(R.layout.fragment_seats) {

    private var _binding: FragmentSeatsBinding? = null
    private val binding get() = _binding!!
    private val selectedSeats = mutableSetOf<String>()

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

        setupSeatSelection()

        binding.buttonContinue.setOnClickListener {
            if (selectedSeats.isEmpty()) {
                Toast.makeText(requireContext(), "Please select at least one seat", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                    putString("selectedDate", selectedDate)
                    putStringArrayList("selectedSeats", ArrayList(selectedSeats))
                    putInt("adultTickets", adultTickets)
                    putInt("seniorTickets", seniorTickets)
                    putInt("studentTickets", studentTickets)
                    putInt("childTickets", childTickets)
                }
                val fragmentManager = (view.context as? AppCompatActivity)?.supportFragmentManager
                val paymentFragment = PaymentFragment()
                paymentFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment, paymentFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupSeatSelection() {
        val rows = arrayOf("A", "B", "C", "D", "E", "F")
        val seatsPerRow = 10

        for (row in rows) {
            for (seatNumber in 1..seatsPerRow) {
                val seatButton = Button(requireContext()).apply {
                    text = "$row$seatNumber"
                    textSize = 7f
                    layoutParams = ViewGroup.LayoutParams(
                        resources.getDimensionPixelSize(R.dimen.seat_button_size),
                        resources.getDimensionPixelSize(R.dimen.seat_button_size)
                    )
                    setOnClickListener { toggleSeatSelection(this) }
                }
                binding.seatGrid.addView(seatButton)
            }
        }
    }

    private fun toggleSeatSelection(button: Button) {
        val seat = button.text.toString()
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat)
            button.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
        } else {
            selectedSeats.add(seat)
            button.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

