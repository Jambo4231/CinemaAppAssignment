package com.example.cinemaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cinemaapp.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment(R.layout.fragment_create_account) {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            (activity as AppCompatActivity).supportFragmentManager.popBackStack()
        }

        binding.buttonContinue.setOnClickListener {
            // Navigate to MyAccountFragment
            val fragmentManager = (view.context as? AppCompatActivity)?.supportFragmentManager
            val myAccountFragment = MyAccountFragment()
            fragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment, myAccountFragment)
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
