package com.example.workingwithrecyclerviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workingwithrecyclerviews.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        // Inside your Fragment
        val adapter = PersonAdapter { selectedPerson ->
            // Handle item click (Checkbox clicked)
            viewModel.setSelectedPerson(selectedPerson)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.recyclerView.adapter = adapter

        viewModel.people.observe(viewLifecycleOwner) { people ->
            // Update the adapter with the new list of people
            adapter.setData(people)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PersonAdapter { person ->
            // Handle item click (Checkbox clicked)
            viewModel.setSelectedPerson(person)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}