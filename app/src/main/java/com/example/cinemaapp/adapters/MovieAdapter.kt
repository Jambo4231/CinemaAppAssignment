package com.example.cinemaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemaapp.R
import com.example.cinemaapp.models.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.movie_title)
        val time: TextView = view.findViewById(R.id.movie_time)
        val image: ImageView = view.findViewById(R.id.movie_image)
        val buyTicketsButton: Button = view.findViewById(R.id.buy_tickets_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.time.text = movie.time
        Glide.with(holder.image.context)
            .load(movie.imageUrl)
            .into(holder.image)

        holder.buyTicketsButton.setOnClickListener {
            onClick(movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}
