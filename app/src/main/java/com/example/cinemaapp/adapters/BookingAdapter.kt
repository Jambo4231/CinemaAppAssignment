package com.example.cinemaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.models.Booking

class BookingAdapter(private val bookings: List<Booking>) : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.booking_title)
        val time: TextView = view.findViewById(R.id.booking_time)
        val seat: TextView = view.findViewById(R.id.booking_seat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.title.text = booking.title
        holder.time.text = booking.time
        holder.seat.text = booking.seat
    }

    override fun getItemCount(): Int = bookings.size
}
