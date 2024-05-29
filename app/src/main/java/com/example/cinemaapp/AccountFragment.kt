package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.example.cinemaapp.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment(R.layout.fragment_account) {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCreateAccount.setOnClickListener {
            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            val createAccountFragment = CreateAccountFragment()

            fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, createAccountFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonLogin.setOnClickListener {
            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            val loginFragment = LoginFragment()

            fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, loginFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
            updateUI()
        }

        updateUI()
    }

    private fun updateUI() {
        if (auth.currentUser != null) {
            // User is logged in
            binding.buttonLogout.visibility = View.VISIBLE
            binding.buttonCreateAccount.visibility = View.GONE
            binding.buttonLogin.visibility = View.GONE
            binding.textView.text = "You are logged in."
        } else {
            // User is not logged in
            binding.buttonLogout.visibility = View.GONE
            binding.buttonCreateAccount.visibility = View.VISIBLE
            binding.buttonLogin.visibility = View.VISIBLE
            binding.textView.text = "Please Log In or Create Account to make a booking. Don’t have an account? Create Account first"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
