package io.erikrios.github.githubuserapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.FragmentFollowersBinding
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.ui.fragments.DetailsFragment.Companion.USERNAME_ARG_KEY
import io.erikrios.github.githubuserapp.viewmodels.MainViewModel

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(USERNAME_ARG_KEY) }?.apply {
            setRecyclerView(getUsers())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(users: List<User>) {
        val userAdapter = UserAdapter(requireContext(), users)
        userAdapter.setOnItemClickListener { user ->
            Toast.makeText(requireContext(), user.name, Toast.LENGTH_SHORT).show()
        }
        binding?.rvFollowers?.adapter = userAdapter
    }

    private fun getUsers(): List<User> {
        val viewModel = MainViewModel(
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