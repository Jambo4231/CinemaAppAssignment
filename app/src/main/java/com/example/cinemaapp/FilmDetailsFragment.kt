package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cinemaapp.databinding.FragmentFilmDetailsBinding
import com.example.cinemaapp.models.Movie
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import android.os.Parcel
import android.os.Parcelable
import java.util.*

class FilmDetailsFragment : Fragment(R.layout.fragment_film_details) {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: String? = null

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

        binding.buttonSelectDate.setOnClickListener {
            val constraintsBuilder = CalendarConstraints.Builder()
            constraintsBuilder.setValidator(DateValidatorFromNow())

            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraintsBuilder.build())
                .build()

            datePicker.addOnPositiveButtonClickListener {
                selectedDate = datePicker.headerText
                binding.selectedDate.text = selectedDate
                binding.buttonContinue.visibility = View.VISIBLE
            }

            datePicker.show(parentFragmentManager, "datePicker")
        }

        binding.buttonContinue.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("movie", movie)
                putString("selectedDate", selectedDate)
            }
            val fragmentManager = (view.context as? AppCompatActivity)?.supportFragmentManager
            val cartFragment = CartFragment()
            cartFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, cartFragment)
                ?.addToBackStack(null)
                ?.commit()
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

class DateValidatorFromNow : CalendarConstraints.DateValidator {

    override fun isValid(date: Long): Boolean {
        return date >= Calendar.getInstance().timeInMillis
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        // no-op
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DateValidatorFromNow> {
        override fun createFromParcel(parcel: Parcel): DateValidatorFromNow {
            return DateValidatorFromNow()
        }

        override fun newArray(size: Int): Array<DateValidatorFromNow?> {
            return arrayOfNulls(size)
        }
    }
}


