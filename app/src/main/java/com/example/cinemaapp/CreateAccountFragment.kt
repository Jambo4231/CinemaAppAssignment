package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateAccountFragment : Fragment(R.layout.fragment_create_account) {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val TAG = "CreateAccountFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonContinue.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()
            val firstName = binding.editTextFirstName.text.toString().trim()
            val lastName = binding.editTextLastName.text.toString().trim()
            val mobileNumber = binding.editTextMobileNumber.text.toString().trim()

            if (validateInput(email, password, confirmPassword, firstName, lastName, mobileNumber)) {
                createAccount(email, password, firstName, lastName, mobileNumber)
            }
        }

        binding.buttonBack.setOnClickListener {
            // Handle back button click
            requireActivity().onBackPressed()
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String, firstName: String, lastName: String, mobileNumber: String): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Valid email required"
            return false
        }

        if (password.isEmpty() || password.length < 8) {
            binding.editTextPassword.error = "Password must be at least 8 characters"
            return false
        }

        if (!password.matches(".*[a-z].*".toRegex())) {
            binding.editTextPassword.error = "Password must contain at least one lowercase letter"
            return false
        }

        if (!password.matches(".*\\d.*".toRegex())) {
            binding.editTextPassword.error = "Password must contain at least one number"
            return false
        }

        if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            binding.editTextPassword.error = "Password must contain at least one special character"
            return false
        }

        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Passwords do not match"
            return false
        }

        if (firstName.isEmpty()) {
            binding.editTextFirstName.error = "First name required"
            return false
        }

        if (lastName.isEmpty()) {
            binding.editTextLastName.error = "Last name required"
            return false
        }

        if (mobileNumber.isEmpty() || !Patterns.PHONE.matcher(mobileNumber).matches()) {
            binding.editTextMobileNumber.error = "Valid mobile number required"
            return false
        }

        return true
    }

    private fun createAccount(email: String, password: String, firstName: String, lastName: String, mobileNumber: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Get user ID
                    val userId = auth.currentUser?.uid

                    // Create user data
                    val user = hashMapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "email" to email,
                        "mobileNumber" to mobileNumber
                    )

                    // Store user data in Firestore
                    userId?.let {
                        firestore.collection("users").document(it).set(user)
                            .addOnSuccessListener {
                                Log.d(TAG, "User data successfully stored in Firestore")
                                // Navigate to MyAccountFragment
                                navigateToMyAccountFragment()
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error storing user data", e)
                            }
                    }
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToMyAccountFragment() {
        val fragmentManager = parentFragmentManager
        val myAccountFragment = MyAccountFragment()

        fragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, myAccountFragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

