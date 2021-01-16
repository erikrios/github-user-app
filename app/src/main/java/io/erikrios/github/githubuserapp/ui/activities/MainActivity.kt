package io.erikrios.github.githubuserapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.ActivityMainBinding
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var users = listOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_GithubUserApp)
        setContentView(view)
        if (users.isEmpty()) {
            users = getUsers()
        }
        setRecyclerView(users)
    }

    private fun setRecyclerView(users: List<User>) {
        val userAdapter = UserAdapter(this, users)
        userAdapter.setOnItemClickListener { user ->
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_USER_KEY, user)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        binding.rvUsers.adapter = userAdapter
    }

    private fun getUsers(): List<User> {
        viewModel = MainViewModel(
            resources.getStringArray(R.array.username),
            resources.getStringArray(R.array.name),
            resources.getStringArray(R.array.location),
            resources.getStringArray(R.array.repository),
            resources.getStringArray(R.array.company),
            resources.getStringArray(R.array.followers),
            resources.getStringArray(R.array.following),
            resources.obtainTypedArray(R.array.avatar)
        )

        return viewModel.getUsers()
    }
}