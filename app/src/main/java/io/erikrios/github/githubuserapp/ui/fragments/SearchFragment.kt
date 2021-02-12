package io.erikrios.github.githubuserapp.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.FragmentSearchBinding
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.repositories.UserRepository
import io.erikrios.github.githubuserapp.ui.viewmodels.SearchViewModel
import io.erikrios.github.githubuserapp.ui.viewmodels.SearchViewModelFactory
import io.erikrios.github.githubuserapp.ui.viewstates.UserResponseViewState

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val factory = SearchViewModelFactory(UserRepository())
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java).apply {
            viewState.observe(viewLifecycleOwner, Observer(this@SearchFragment::handleState))
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToSettings()
        handleSearch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRecyclerView(users: List<User>) {
        val userAdapter = UserAdapter(requireContext(), users)
        userAdapter.setOnItemClickListener { user ->
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(user)
            findNavController().navigate(action)
        }

        binding?.rvUsers?.adapter = userAdapter
    }

    private fun navigateToSettings() {
        binding?.toolbar?.menu?.findItem(R.id.item_settings)?.setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_settingsFragment)
            return@setOnMenuItemClickListener true
        }
    }

    private fun handleSearch() {
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView =
            binding?.toolbar?.menu?.findItem(R.id.item_search)?.actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = getString(R.string.query_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.searchUsers(query as String)
                    clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun handleState(viewState: UserResponseViewState?) {
        viewState?.let { userResponseViewState ->
            showLoading(userResponseViewState.loading)

            userResponseViewState.userResponse?.let { userResponse ->
                if (userResponse.users.isNotEmpty()) {
                    setRecyclerView(userResponse.users)
                    binding?.rvUsers?.visibility = View.VISIBLE
                    binding?.lavNotFound?.visibility = View.GONE
                } else {
                    binding?.rvUsers?.visibility = View.GONE
                    binding?.lavNotFound?.visibility = View.VISIBLE
                }
            }

            userResponseViewState.exception?.let { exception ->
                showError(exception)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.apply {
                progressBar.visibility = View.VISIBLE
                rvUsers.visibility = View.GONE
                lavNotFound.visibility = View.GONE
            }
        } else {
            binding?.apply {
                progressBar.visibility = View.GONE
                rvUsers.visibility = View.VISIBLE
                lavNotFound.visibility = View.VISIBLE
            }
        }
    }

    private fun showError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }
}