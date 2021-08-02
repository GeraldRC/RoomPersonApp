package com.example.roomapp.viewmodel

import androidx.lifecycle.*
import com.example.roomapp.db.PersonRepo
import com.example.roomapp.model.Person
import kotlinx.coroutines.launch

class PersonViewModel(private val repository: PersonRepo): ViewModel() {

    val allPersons: LiveData<List<Person>> = repository.allPerson.asLiveData()


    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

}

class WordViewModelFactory(private val repository: PersonRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}