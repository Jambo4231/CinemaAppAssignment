package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            val myAccountFragment = MyAccountFragment()

            fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, myAccountFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonForgottenPassword.setOnClickListener {
            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            val resetPasswordFragment = ResetPasswordFragment()

            fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, resetPasswordFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonBack.setOnClickListener {
            (activity as AppCompatActivity).supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
