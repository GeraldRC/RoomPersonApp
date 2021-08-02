package com.example.roomapp

-+

import android.app.Application
import com.example.roomapp.db.PersonDb
import com.example.roomapp.db.PersonRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PersonApp: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { PersonDb.getDatabase(this,applicationScope) }
    val personRepo by lazy { PersonRepo(database.personDao()) }

}