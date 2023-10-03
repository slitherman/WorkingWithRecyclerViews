package com.example.workingwithrecyclerviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.workingwithrecyclerviews.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = binding.etNameFrag2
        val etAge = binding.etAgeFrag2
        val etAddress = binding.etAddressFrag2
        val btnUpdate = binding.btnUpdateFrag2
        val btnAdd = binding.btnAddFrag2
        val btnDelete = binding.btneleteFrag2

        // Check if there is a selected person in the ViewModel
        viewModel.selectedPerson.observe(viewLifecycleOwner) { person ->
            etName.setText(person?.name)
            etAge.setText(person?.age.toString())
            etAddress.setText(person?.address)
        }

        btnUpdate.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toIntOrNull() ?: 0
            val address = etAddress.text.toString()

            val selectedPerson = viewModel.selectedPerson.value

            if (selectedPerson != null) {
                val updatedPerson = Person(name, age, address, selectedPerson.id)
                viewModel.updatePerson(selectedPerson.id, updatedPerson)
            }
        }

        btnAdd.setOnClickListener {
            // Add your logic for adding a new person here
        }

        btnDelete.setOnClickListener {
            val selectedPerson = viewModel.selectedPerson.value
            if (selectedPerson != null) {
                viewModel.deletePerson(selectedPerson.id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
