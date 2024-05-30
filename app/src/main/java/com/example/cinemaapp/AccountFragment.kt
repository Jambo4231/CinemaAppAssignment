package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

        // Check if the user is already logged in
        if (auth.currentUser != null) {
            navigateToMyAccountFragment()
        } else {
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
        }
    }

    private fun navigateToMyAccountFragment() {
        val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
        val myAccountFragment = MyAccountFragment()

        fragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, myAccountFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


