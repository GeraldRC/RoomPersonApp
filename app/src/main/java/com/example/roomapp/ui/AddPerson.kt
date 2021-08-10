package com.example.roomapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.roomapp.databinding.ActivityAddPersonBinding

class AddPerson : AppCompatActivity() {


    private lateinit var binding: ActivityAddPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.btnPerson

        button.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val name = binding.txtName.text.toString()
            val lastName = binding.txtApe.text.toString()
            val age = binding.txtAge.text.toString()


            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age)){
                setResult(Activity.RESULT_CANCELED,intent)
            }else{
                intent.putExtra("name",name)
                intent.putExtra("lastname",lastName)
                intent.putExtra("age",age.toInt())
                setResult(Activity.RESULT_OK,intent)
            }
            finish()
        }
    }
}
