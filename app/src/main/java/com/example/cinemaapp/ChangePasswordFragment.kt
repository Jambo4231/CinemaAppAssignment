package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChange.setOnClickListener {
            navigateToFragment(MyAccountFragment())
        }

        binding.buttonBack.setOnClickListener {
            (view.context as? AppCompatActivity)?.supportFragmentManager?.popBackStack()
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager = (view?.context as? AppCompatActivity)?.supportFragmentManager
        fragmentManager?.beginTransaction()
            ?.replace(R.id.nav_host_fragment, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
