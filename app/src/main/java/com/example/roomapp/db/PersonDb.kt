package com.example.roomapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomapp.model.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
abstract class PersonDb: RoomDatabase() {

    abstract fun personDao(): PersonDao

    private class PersonDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val personDao = database.personDao()

                    // Delete all content here.
                    personDao.deleteAll()

                    // Add sample words.
                    var person = Person(0,"Gerald","Reyes",29)
                    personDao.insert(person)

                    person = Person(0,"Alonso","Reyes",3)
                    personDao.insert(person)
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PersonDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PersonDb {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDb::class.java,
                    "person_database"
                )
                    .addCallback(PersonDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}