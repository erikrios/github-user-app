package io.erikrios.github.githubuserapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.erikrios.github.githubuserapp.databinding.ActivityDetailsBinding
import io.erikrios.github.githubuserapp.models.User

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val EXTRA_USER_KEY = "extra_user_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val user = intent.getParcelableExtra<User>(EXTRA_USER_KEY) as User
        setActionBar(user.name)
        binding.apply {
            imgAvatar.setImageResource(user.avatar)
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvUsername.text = user.username
            tvFollowers.text = user.followers.toString()
            tvRepository.text = user.repository.toString()
            tvFollowing.text = user.following.toString()
        }
    }

    private fun setActionBar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}