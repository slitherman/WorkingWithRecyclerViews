package com.example.workingwithrecyclerviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel : ViewModel() {
    // Properties to manage the list of people
    private var nextId = 1
    private var _peopleList = mutableListOf<Person>(
        Person("Lachy", 12, "wollongong", nextId++),
        Person("Mathew", 9, "melbourne", nextId++),
        Person("elijah", 40, "gold coast", nextId++),
    )

    // MutableLiveData to hold the list of people
    private var _people = MutableLiveData<List<Person>>(_peopleList)

    // LiveData to expose the list of people as read-only
    val people: LiveData<List<Person>> = _people

    // MutableLiveData to hold the selected person for editing in SecondFragment
    val selectedPerson = MutableLiveData<Person>()

    // Function to set the selected person for editing
    fun setSelectedPerson(person: Person) {
        selectedPerson.value = person
    }

    // Function to add a new person to the list
    fun addPerson(person: Person) {
        person.id = nextId
        _peopleList.add(person)
        _people.value = _peopleList
    }

    // Function to update an existing person in the list
    fun updatePerson(id: Int, updatedPerson: Person) {
        val existingPerson = _peopleList.find { it.id == id }
        existingPerson?.apply {
            name = updatedPerson.name
            age = updatedPerson.age
            address = updatedPerson.address
        }
        _people.value = _peopleList
    }

    // Function to delete a person from the list by their ID
    fun deletePerson(id: Int) {
        _peopleList.removeAll { it.id == id }
        _people.value = _peopleList
    }
}
