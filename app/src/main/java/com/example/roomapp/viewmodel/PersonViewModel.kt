package com.example.roomapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.db.PersonRepo
import com.example.roomapp.model.Person
import kotlinx.coroutines.launch

class PersonViewModel(private val repository: PersonRepo): ViewModel() {

    val allPersons: LiveData<List<Person>> = repository.allPerson.asLiveData()


    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }
}