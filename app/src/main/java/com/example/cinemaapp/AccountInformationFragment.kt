package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentAccountInformationBinding

class AccountInformationFragment : Fragment(R.layout.fragment_account_information) {

    private var _binding: FragmentAccountInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdateInfo.setOnClickListener {
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
