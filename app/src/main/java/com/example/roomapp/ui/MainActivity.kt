package com.example.roomapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.PersonApp
import com.example.roomapp.adapter.PersonAdapter
import com.example.roomapp.databinding.ActivityMainBinding
import com.example.roomapp.model.Person
import com.example.roomapp.viewmodel.PersonViewModel
import com.example.roomapp.viewmodel.PersonViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val wordViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonApp).personRepo)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val name = result.data?.getStringExtra("name")
            val lastName = result.data?.getStringExtra("lastname")
            val age = result.data?.getIntExtra("age",0)

            val person = Person(id = 0,name!!,lastName!!,age!!)

            wordViewModel.insert(person)
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this,AddPerson::class.java)
            startForResult.launch(intent)
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