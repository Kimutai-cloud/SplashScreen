package com.example.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.splashscreen.databinding.FragmentFourthBinding
import com.example.splashscreen.databinding.FragmentThirdBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button to navigate to the FirstFragment
        binding.buttonGoToFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FourthFragment_to_FirstFragment)
        }

        // Button to navigate to the SecondFragment
        binding.buttonGoToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_FourthFragment_to_SecondFragment)
        }

        // Button to navigate to the ThirdFragment
        binding.buttonGoToThird.setOnClickListener {
            findNavController().navigate(R.id.action_FourthFragment_to_ThirdFragment)
        }

        // Button to navigate to the FifthFragment
        binding.buttonGoToFifth.setOnClickListener {
            findNavController().navigate(R.id.action_FourthFragment_to_FifthFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}