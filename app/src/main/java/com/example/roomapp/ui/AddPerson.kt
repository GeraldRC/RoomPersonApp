package com.example.roomapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomapp.databinding.ActivityAddPersonBinding

class AddPerson : AppCompatActivity() {
    private lateinit var binding: ActivityAddPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}