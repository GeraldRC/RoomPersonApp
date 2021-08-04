package com.example.roomapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.PersonApp
import com.example.roomapp.adapter.PersonAdapter
import com.example.roomapp.databinding.ActivityMainBinding
import com.example.roomapp.viewmodel.PersonViewModel
import com.example.roomapp.viewmodel.PersonViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val wordViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonApp).personRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this,AddPerson::class.java)
            startActivity(intent)
        }

       initRecycler()
    }

    private fun initRecycler() {
        binding.recyvlerPerson.layoutManager = LinearLayoutManager(this)
        val adapter = PersonAdapter()
        binding.recyvlerPerson.adapter = adapter

        wordViewModel.allPersons.observe(this){ person ->
            person.let { adapter.submitList(it) }
        }
    }
}