package com.example.cinemaapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cinemaapp.databinding.FragmentFilmDetailsBinding
import com.example.cinemaapp.models.Movie
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonSelectDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.buttonContinue.setOnClickListener {
            navigateToCartFragment()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                updateSelectedDate(calendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateSelectedDate(calendar: Calendar) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        binding.textSelectedDate.text = dateFormat.format(calendar.time)
        binding.buttonContinue.visibility = View.VISIBLE
    }

    private fun navigateToCartFragment() {
        val cartFragment = CartFragment()

        (activity as? AppCompatActivity)?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment, cartFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
