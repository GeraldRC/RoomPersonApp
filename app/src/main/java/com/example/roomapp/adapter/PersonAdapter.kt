package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.R
import com.example.roomapp.databinding.ItemPersonBinding
import com.example.roomapp.model.Person

class PersonAdapter(private val persons: List<Person>): RecyclerView.Adapter<PersonAdapter.PersonHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PersonHolder(layoutInflater.inflate(R.layout.item_person,parent,false))
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.render(persons[position])
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    class PersonHolder(private val view: View):RecyclerView.ViewHolder(view){

        private val binding = ItemPersonBinding.bind(view)

        fun render(person: Person){
            binding.tvName.text = person.name
            binding.tvLastName.text = person.lastName
            binding.tvAge.text = person.age.toString()

            binding.tvName.setOnClickListener {
                Toast.makeText(view.context,"Nombre:${binding.tvName.text}",Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}