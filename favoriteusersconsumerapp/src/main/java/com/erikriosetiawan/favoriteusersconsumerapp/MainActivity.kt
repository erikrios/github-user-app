package com.erikriosetiawan.favoriteusersconsumerapp

import android.content.Intent
import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.favoriteusersconsumerapp.DetailsActivity.Companion.EXTRA_USER_KEY
import com.erikriosetiawan.favoriteusersconsumerapp.adapters.UserAdapter
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.erikriosetiawan.favoriteusersconsumerapp.databinding.ActivityMainBinding
import com.erikriosetiawan.favoriteusersconsumerapp.helpers.MappingHelper
import com.erikriosetiawan.favoriteusersconsumerapp.models.User
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var users: ArrayList<User>? = null

    companion object {
        private const val EXTRA_STATE = "extra_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loadUsersAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, false, myObserver)

        if (savedInstanceState == null) {
            loadUsersAsync()
        } else {
            users = savedInstanceState.getParcelableArrayList(EXTRA_STATE)
            users?.let { setRecyclerView(it) }
        }
    }

    override fun onResume() {
        super.onResume()
        loadUsersAsync()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, users)
    }

    private fun loadUsersAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.progressBar.visibility = View.VISIBLE
            val deferred = async(Dispatchers.IO) {
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }
            users = deferred.await()
            binding.progressBar.visibility = View.GONE
            users?.let {
                if (it.isNotEmpty()) {
                    setRecyclerView(it)
                } else {
                    setRecyclerView(it)
                    Snackbar.make(
                        binding.rvUsers,
                        getString(R.string.favorite_user_empty),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setRecyclerView(users: List<User>) {
        val adapter = UserAdapter(this, users)
        adapter.setOnItemClickListener { user ->
            Intent(this, DetailsActivity::class.java).also {
                it.putExtra(EXTRA_USER_KEY, user)
                startActivity(it)
            }
        }
        binding.rvUsers.adapter = adapter
    }
}