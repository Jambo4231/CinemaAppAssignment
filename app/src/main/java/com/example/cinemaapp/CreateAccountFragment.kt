package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccountFragment : Fragment(R.layout.fragment_create_account) {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonContinue.setOnClickListener {
            Log.d("CreateAccountFragment", "Continue button clicked")
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editTextEmail.error = "Valid email required"
                Log.d("CreateAccountFragment", "Invalid email")
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                binding.editTextPassword.error = "Password must be at least 8 characters"
                Log.d("CreateAccountFragment", "Invalid password")
                return@setOnClickListener
            }

            Log.d("CreateAccountFragment", "Creating account with email: $email")
            createAccount(email, password)
        }

        binding.buttonBack.setOnClickListener {
            (view.context as? AppCompatActivity)?.supportFragmentManager?.popBackStack()
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("CreateAccountFragment", "Account creation successful")
                    Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                    navigateToFragment(MyAccountFragment())
                } else {
                    Log.d("CreateAccountFragment", "Account creation failed: ${task.exception?.message}")
                    Toast.makeText(context, "Account creation failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
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
