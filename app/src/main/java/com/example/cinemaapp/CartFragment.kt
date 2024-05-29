package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentCartBinding
import com.example.cinemaapp.models.Movie

class CartFragment : Fragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private var adultTickets = 0
    private var seniorTickets = 0
    private var studentTickets = 0
    private var childTickets = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: Movie? = arguments?.getParcelable("movie")
        val selectedDate = arguments?.getString("selectedDate")

        movie?.let {
            binding.cartFilmTitle.text = it.title
        }
        binding.cartSelectedDate.text = selectedDate

        setupTicketButtons()

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonCheckout.setOnClickListener {
            if (adultTickets + seniorTickets + studentTickets + childTickets == 0) {
                Toast.makeText(requireContext(), "Please add at least one ticket", Toast.LENGTH_SHORT).show()
            } else {
                // Pass data to SeatsFragment
                val bundle = Bundle().apply {
                    putParcelable("movie", movie)
                    putString("selectedDate", selectedDate)
                    putInt("adultTickets", adultTickets)
                    putInt("seniorTickets", seniorTickets)
                    putInt("studentTickets", studentTickets)
                    putInt("childTickets", childTickets)
                }
                val fragmentManager = (view.context as? AppCompatActivity)?.supportFragmentManager
                val seatsFragment = SeatsFragment()
                seatsFragment.arguments = bundle
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.nav_host_fragment, seatsFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
    }

    private fun setupTicketButtons() {
        binding.buttonAddAdult.setOnClickListener {
            adultTickets++
            updateTicketCount()
        }
        binding.buttonMinusAdult.setOnClickListener {
            if (adultTickets > 0) {
                adultTickets--
                updateTicketCount()
            }
        }

        binding.buttonAddSenior.setOnClickListener {
            seniorTickets++
            updateTicketCount()
        }
        binding.buttonMinusSenior.setOnClickListener {
            if (seniorTickets > 0) {
                seniorTickets--
                updateTicketCount()
            }
        }

        binding.buttonAddStudent.setOnClickListener {
            studentTickets++
            updateTicketCount()
        }
        binding.buttonMinusStudent.setOnClickListener {
            if (studentTickets > 0) {
                studentTickets--
                updateTicketCount()
            }
        }

        binding.buttonAddChild.setOnClickListener {
            childTickets++
            updateTicketCount()
        }
        binding.buttonMinusChild.setOnClickListener {
            if (childTickets > 0) {
                childTickets--
                updateTicketCount()
            }
        }
    }

    private fun updateTicketCount() {
        binding.adultTicketCount.text = adultTickets.toString()
        binding.seniorTicketCount.text = seniorTickets.toString()
        binding.studentTicketCount.text = studentTickets.toString()
        binding.childTicketCount.text = childTickets.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
