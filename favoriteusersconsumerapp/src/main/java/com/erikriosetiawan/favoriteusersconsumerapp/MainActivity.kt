package com.erikriosetiawan.favoriteusersconsumerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.favoriteusersconsumerapp.adapters.UserAdapter
import com.erikriosetiawan.favoriteusersconsumerapp.databinding.ActivityMainBinding
import com.erikriosetiawan.favoriteusersconsumerapp.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setRecyclerView(users: List<User>) {
        val adapter = UserAdapter(this, users)
        adapter.setOnItemClickListener { user ->
            Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
        }
        binding.rvUsers.adapter = adapter
    }
}