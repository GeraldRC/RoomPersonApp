package com.example.roomapp.db


import androidx.annotation.WorkerThread
import com.example.roomapp.model.Person
import kotlinx.coroutines.flow.Flow

class PersonRepo(private val personDao: PersonDao) {

    val allPerson: Flow<List<Person>> = personDao.getAll()

    @WorkerThread
    suspend fun insert(person: Person){
        personDao.insert(person)
    }
}