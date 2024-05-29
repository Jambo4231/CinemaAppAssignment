package com.example.cinemaapp

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentChangePasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

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
            val newPassword = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

            if (newPassword.isEmpty() || newPassword.length < 8) {
                binding.editTextPassword.error = "Password must be at least 8 characters"
                return@setOnClickListener
            }

            if (!newPassword.matches(".*[a-z].*".toRegex())) {
                binding.editTextPassword.error = "Password must contain at least one lowercase letter"
                return@setOnClickListener
            }

            if (!newPassword.matches(".*\\d.*".toRegex())) {
                binding.editTextPassword.error = "Password must contain at least one number"
                return@setOnClickListener
            }

            if (!newPassword.matches(".*[@#\$%^&+=].*".toRegex())) {
                binding.editTextPassword.error = "Password must contain at least one special character"
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                binding.editTextConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            updatePassword(newPassword)
        }

        binding.buttonBack.setOnClickListener {
            (view.context as? AppCompatActivity)?.supportFragmentManager?.popBackStack()
        }
    }

    private fun updatePassword(newPassword: String) {
        val user = auth.currentUser
        if (user != null) {
            user.updatePassword(newPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        updateFirestorePassword(newPassword)
                    } else {
                        Toast.makeText(requireContext(), "Error updating password: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun updateFirestorePassword(newPassword: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            firestore.collection("users").document(userId)
                .update("password", newPassword)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Password updated successfully", Toast.LENGTH_SHORT).show()
                    navigateToFragment(MyAccountFragment())
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Error updating Firestore: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
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
