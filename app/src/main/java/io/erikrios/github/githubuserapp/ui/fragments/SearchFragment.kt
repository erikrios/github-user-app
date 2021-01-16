package io.erikrios.github.githubuserapp.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.FragmentSearchBinding
import io.erikrios.github.githubuserapp.models.User
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
        handleSearch()
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
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(user)
            findNavController().navigate(action)
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

    private fun handleSearch() {
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView =
            binding?.toolbar?.menu?.findItem(R.id.item_search)?.actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = getString(R.string.query_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i(SearchFragment::class.java.simpleName, query as String)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }
}