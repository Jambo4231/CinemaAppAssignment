package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
            navigateToFragment(ChangePasswordFragment())
        }

        binding.buttonAccountInformation.setOnClickListener {
            navigateToFragment(AccountInformationFragment())
        }

        binding.buttonLogout.setOnClickListener {
            // Implement logout functionality
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
