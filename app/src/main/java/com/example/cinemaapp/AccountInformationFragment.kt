package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentAccountInformationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AccountInformationFragment : Fragment(R.layout.fragment_account_information) {

    private var _binding: FragmentAccountInformationBinding? = null
    private val binding get() = _binding!!
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUserInfo()

        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonUpdateInfo.setOnClickListener {
            updateUserInfo()
        }
    }

    private fun loadUserInfo() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            firestore.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        binding.editTextFirstName.setText(document.getString("firstName"))
                        binding.editTextLastName.setText(document.getString("lastName"))
                        binding.editTextEmail.setText(document.getString("email"))
                        binding.editTextMobileNumber.setText(document.getString("mobileNumber"))
                    } else {
                        Toast.makeText(requireContext(), "No user data found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Error fetching user data", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUserInfo() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userUpdates = hashMapOf<String, Any>(
                "firstName" to binding.editTextFirstName.text.toString(),
                "lastName" to binding.editTextLastName.text.toString(),
                "email" to binding.editTextEmail.text.toString(),
                "mobileNumber" to binding.editTextMobileNumber.text.toString()
            )

            firestore.collection("users").document(userId).update(userUpdates)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "User info updated", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Error updating user info", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
