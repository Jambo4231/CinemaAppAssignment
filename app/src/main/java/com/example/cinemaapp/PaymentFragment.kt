package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentPaymentBinding
import com.example.cinemaapp.models.Movie

class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie? = arguments?.getParcelable("movie")
        val selectedDate = arguments?.getString("selectedDate")
        val selectedSeats = arguments?.getStringArrayList("selectedSeats") ?: emptyList()
        val adultTickets = arguments?.getInt("adultTickets") ?: 0
        val seniorTickets = arguments?.getInt("seniorTickets") ?: 0
        val studentTickets = arguments?.getInt("studentTickets") ?: 0
        val childTickets = arguments?.getInt("childTickets") ?: 0

        movie?.let {
            binding.paymentFilmTitle.text = it.title
            binding.paymentSelectedDate.text = selectedDate
        }

        binding.paymentSelectedSeats.text = "Selected Seats: ${selectedSeats.joinToString(", ")}"

        val ticketSummary = StringBuilder()
        var totalCost = 0

        if (adultTickets > 0) {
            val cost = adultTickets * 7
            ticketSummary.append("Adult Tickets: $adultTickets x £7 = £$cost\n")
            totalCost += cost
        }

        if (seniorTickets > 0) {
            val cost = seniorTickets * 6
            ticketSummary.append("Senior Tickets: $seniorTickets x £6 = £$cost\n")
            totalCost += cost
        }

        if (studentTickets > 0) {
            val cost = studentTickets * 6
            ticketSummary.append("Student Tickets: $studentTickets x £6 = £$cost\n")
            totalCost += cost
        }

        if (childTickets > 0) {
            val cost = childTickets * 5
            ticketSummary.append("Child Tickets: $childTickets x £5 = £$cost\n")
            totalCost += cost
        }

        ticketSummary.append("\nTotal: £$totalCost")

        binding.paymentTicketSummary.text = ticketSummary.toString()

        binding.buttonPay.setOnClickListener {
            // Handle the payment process
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

