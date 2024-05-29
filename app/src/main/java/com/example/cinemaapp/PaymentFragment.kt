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

        binding.paymentSelectedSeats.text = selectedSeats.joinToString(", ")

        val totalCost = (adultTickets * 7) + (seniorTickets * 6) + (studentTickets * 6) + (childTickets * 5)
        binding.paymentTotalCost.text = "Total: £$totalCost"

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
