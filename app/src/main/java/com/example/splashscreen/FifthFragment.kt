package com.example.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.splashscreen.databinding.FragmentFifthBinding
import com.example.splashscreen.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button to navigate to the FirstFragment
        binding.buttonBackToFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_FirstFragment)
        }

        // Button to navigate to the SecondFragment
        binding.buttonGoToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_SecondFragment)
        }

        // Button to navigate to the FourthFragment
        binding.buttonGoToFourth.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_FourthFragment)
        }

        // Button to navigate to the ThirdFragment
        binding.buttonGoToThird.setOnClickListener {
            findNavController().navigate(R.id.action_FifthFragment_to_ThirdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}