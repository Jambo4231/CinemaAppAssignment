package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentMyAccountBinding

class MyAccountFragment : Fragment(R.layout.fragment_my_account) {

    private var _binding: FragmentMyAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_myAccountFragment_to_changePasswordFragment)
        }

        binding.buttonAccountInformation.setOnClickListener {
            findNavController().navigate(R.id.action_myAccountFragment_to_accountInformationFragment)
        }

        binding.buttonLogout.setOnClickListener {
            // Implement logout functionality
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
