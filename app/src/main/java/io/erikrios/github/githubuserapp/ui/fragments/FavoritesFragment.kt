package io.erikrios.github.githubuserapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databases.UserDatabase
import io.erikrios.github.githubuserapp.databinding.FragmentFavoritesBinding
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.repositories.UserRepository
import io.erikrios.github.githubuserapp.ui.viewmodels.FavoritesViewModel
import io.erikrios.github.githubuserapp.ui.viewmodels.ViewModelFactory
import io.erikrios.github.githubuserapp.ui.viewstates.UsersViewState

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val repository = UserRepository(UserDatabase(requireContext()))
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(FavoritesViewModel::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
        viewModel.usersViewState.observe(viewLifecycleOwner, this@FavoritesFragment::handleState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleToolbar() {
        binding?.toolbar?.apply {
            navigationIcon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun handleState(usersViewState: UsersViewState) {
        handleLoading(usersViewState.loading)
        usersViewState.users?.let { handleSuccess(it) }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                rvUsers.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                rvUsers.visibility = View.VISIBLE
            }
        }
    }

    private fun handleSuccess(users: List<User>) {
        if (users.isEmpty()) {
            binding?.apply {
                rvUsers.visibility = View.GONE
                lavEmpty.visibility = View.VISIBLE
            }
        } else {
            binding?.apply {
                lavEmpty.visibility = View.GONE
                rvUsers.visibility = View.VISIBLE
                setRecyclerView(users)
            }
        }
    }

    private fun setRecyclerView(users: List<User>) {
        val userAdapter = UserAdapter(requireContext(), users)
        userAdapter.setOnItemClickListener { user ->
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(user)
            findNavController().navigate(action)
        }
        binding?.rvUsers?.adapter = userAdapter
    }
}