package io.erikrios.github.githubuserapp.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.ActivityMainBinding
import io.erikrios.github.githubuserapp.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setRecyclerView(users: List<User>) {
        val userAdapter = UserAdapter(this, users)
        userAdapter.setOnItemClickListener { user ->
            Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
        }

        binding.rvUsers.adapter = userAdapter
    }
}