package io.erikrios.github.githubuserapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.adapters.DetailsPagerAdapter
import io.erikrios.github.githubuserapp.databinding.FragmentsDetailsBinding
import io.erikrios.github.githubuserapp.models.User

class DetailsFragment : Fragment() {

    private var _binding: FragmentsDetailsBinding? = null
    private val binding get() = _binding
    private val args: DetailsFragmentArgs by navArgs()

    companion object {
        const val USERNAME_ARG_KEY = "username_arg_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentsDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            val user = args.user

            Glide.with(requireContext()).load(user.avatarUrl).into(imgAvatar)
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvUsername.text = user.username
            tvFollowers.text = user.followers.toString()
            tvRepository.text = user.publicRepositories.toString()
            tvFollowing.text = user.following.toString()

            fabShare.setOnClickListener {
                share(user)
            }

            fabOpenInBrowser.setOnClickListener {
                openInBrowser(user)
            }

            toolbar.apply {
                title = user.name
                navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            handleTabs(user.username, user.followers as Int, user.following as Int)
        }
    }

    private fun openInBrowser(user: User) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl))
        startActivity(intent)
    }

    private fun share(user: User) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_message, user.name, user.htmlUrl)
        )
        intent.type = "text/plain"
        startActivity(intent)
    }

    private fun handleTabs(username: String, followers: Int, following: Int) {
        val fragments = listOf(FollowersFragment(), FollowingFragment())
        binding?.viewPager2?.adapter =
            DetailsPagerAdapter(requireActivity(), fragments, USERNAME_ARG_KEY, username)
        TabLayoutMediator(
            binding?.tabLayout as TabLayout,
            binding?.viewPager2 as ViewPager2
        ) { tab, position ->
            tab.apply {
                when (position) {
                    0 -> {
                        text = getString(R.string.followers)
                        val badgeDrawable = orCreateBadge
                        badgeDrawable.backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.purple_200)
                        badgeDrawable.isVisible = true
                        badgeDrawable.number = followers
                    }
                    1 -> {
                        text = getString(R.string.following)
                        val badgeDrawable = orCreateBadge
                        badgeDrawable.backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.purple_200)
                        badgeDrawable.isVisible = true
                        badgeDrawable.number = following
                    }
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}