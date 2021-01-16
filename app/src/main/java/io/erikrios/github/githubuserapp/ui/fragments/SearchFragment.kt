package io.erikrios.github.githubuserapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.FragmentSearchBinding
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.ui.activities.DetailsActivity
import io.erikrios.github.githubuserapp.viewmodels.MainViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: MainViewModel
    private var users = listOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (users.isEmpty()) {
            users = getUsers()
        }
        setRecyclerView(users)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(users: List<User>) {
        val userAdapter = UserAdapter(requireContext(), users)
        userAdapter.setOnItemClickListener { user ->
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_USER_KEY, user)
            startActivity(intent)
        }

        binding?.rvUsers?.adapter = userAdapter
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