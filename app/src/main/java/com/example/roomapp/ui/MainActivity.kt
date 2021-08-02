package com.example.roomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.R
import com.example.roomapp.adapter.PersonAdapter
import com.example.roomapp.databinding.ActivityMainBinding
import com.example.roomapp.model.Person

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val persons: List<Person> = listOf(
        Person(1,"Matias","Reyes",29),
        Person(2,"Alonso","Reyes",3),
        Person(3,"Alex","Coria",11),
        Person(4,"Luz","Coria",29),
        Person(1,"Matias","Reyes",29),
        Person(2,"Alonso","Reyes",3),
        Person(3,"Alex","Coria",11),
        Person(4,"Luz","Coria",29)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {
        binding.recyvlerPerson.layoutManager = LinearLayoutManager(this)
        val adapter = PersonAdapter(persons)
        binding.recyvlerPerson.adapter = adapter
    }
}