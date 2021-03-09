package com.erikriosetiawan.favoriteusersconsumerapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.toContentValues
import com.erikriosetiawan.favoriteusersconsumerapp.databinding.ActivityDetailsBinding
import com.erikriosetiawan.favoriteusersconsumerapp.models.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var isFavorite = true

    companion object {
        const val EXTRA_USER_KEY = "extra_user_key"
        const val IS_FAVORITE_KEY = "is_favorite_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.let {
            isFavorite = it.getBoolean(IS_FAVORITE_KEY)
        }

        val user = intent.getParcelableExtra<User>(EXTRA_USER_KEY) as User
        val uriWithId = Uri.parse("$CONTENT_URI/${user.id}")
        setActionBar(user.name ?: getString(R.string.user_details))
        binding.apply {
            Glide.with(this@DetailsActivity).load(user.avatarUrl).into(imgAvatar)
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvUsername.text = user.username
            tvFollowers.text = user.followers.toString()
            tvRepository.text = user.publicRepositories.toString()
            tvFollowing.text = user.following.toString()

            fabFavorite.setOnClickListener {
                if (isFavorite) {
                    deleteUserAsync(uriWithId)
                    fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                } else {
                    insertUserAsync(user)
                    fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
            }

            fabOpenInBrowser.setOnClickListener {
                openInBrowser(user)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_FAVORITE_KEY, isFavorite)
    }

    private fun openInBrowser(user: User) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
        startActivity(intent)
    }

    private fun setActionBar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun deleteUserAsync(uri: Uri) {
        GlobalScope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                contentResolver.delete(uri, null, null)
            }
            val affectedRows = deferred.await()
            if (affectedRows > 1) {
                isFavorite = false
                Snackbar.make(
                    binding.coordinatorLayout,
                    getString(R.string.remove_favorite_message),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun insertUserAsync(user: User) {
        val contentValues = toContentValues(user)
        GlobalScope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                contentResolver.insert(CONTENT_URI, contentValues)
            }
            val uriResult = deferred.await()
            uriResult?.let {
                isFavorite = true
                Snackbar.make(
                    binding.coordinatorLayout,
                    getString(R.string.add_favorite_message),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}