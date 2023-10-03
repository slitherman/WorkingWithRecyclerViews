package com.example.workingwithrecyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workingwithrecyclerviews.databinding.ItemPersonBinding

class PersonAdapter(private val onItemClicked: (Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private val people: MutableList<Person> = mutableListOf()

    inner class ViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(person: Person) {
            binding.apply {
                textViewName.text = "Name: ${person.name}"
                textViewAge.text = "Age: ${person.age}"
                textViewAddress.text = "Address: ${person.address}"
                cbNavigate.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) onItemClicked(person)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = people[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return people.size
    }

    // Function to set the data in the adapter
    fun setData(newPeople: List<Person>) {
        people.clear()
        people.addAll(newPeople)
        notifyDataSetChanged()
    }
}