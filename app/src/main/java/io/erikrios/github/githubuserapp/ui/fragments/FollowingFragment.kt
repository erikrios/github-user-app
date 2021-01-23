package io.erikrios.github.githubuserapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.erikrios.github.githubuserapp.adapters.UserAdapter
import io.erikrios.github.githubuserapp.databinding.FragmentFollowingBinding
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.ui.fragments.DetailsFragment.Companion.USERNAME_ARG_KEY
import io.erikrios.github.githubuserapp.ui.viewstates.UsersViewState

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(USERNAME_ARG_KEY) }?.apply {
            val viewModel = (parentFragment as DetailsFragment).viewModel
            viewModel.followingViewState.observe(
                viewLifecycleOwner,
                Observer(this@FollowingFragment::handleState)
            )
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
        binding?.rvFollowing?.adapter = userAdapter
    }

    private fun handleState(viewState: UsersViewState?) {
        viewState?.let { followingViewState ->
            showLoading(followingViewState.loading)

            followingViewState.users?.let { user ->
                setRecyclerView(user)
            }

            followingViewState.exception?.let { exception ->
                showError(exception)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }
}