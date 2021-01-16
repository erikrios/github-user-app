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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.Theme_GithubUserApp)
        setContentView(view)
    }
}