package com.example.roomapp.db

import androidx.room.*
import com.example.roomapp.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM PERSON")
    fun getAll(): Flow<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Query("DELETE FROM PERSON")
    suspend fun deleteAll()
}