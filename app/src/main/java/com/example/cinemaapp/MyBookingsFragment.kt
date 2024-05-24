package com.example.cinemaapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.adapters.BookingAdapter
import com.example.cinemaapp.models.Booking

class MyBookingsFragment : Fragment(R.layout.fragment_my_bookings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_my_bookings)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BookingAdapter(getMyBookings()) // Implement the adapter and data source
    }

    private fun getMyBookings(): List<Booking> {
        // Fetch or create a list of user's bookings
        return listOf(
            // Example data
            Booking("Godzilla x Kong: The New Empire", "17:00", "Row A, Seat 12"),
            Booking("The Joker: Folie A Deux", "16:40", "Row B, Seat 5")
        )
    }
}
