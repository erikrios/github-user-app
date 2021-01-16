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
import io.erikrios.github.githubuserapp.R
import io.erikrios.github.githubuserapp.databinding.FragmentsDetailsBinding
import io.erikrios.github.githubuserapp.models.User

class DetailsFragment : Fragment() {

    private var _binding: FragmentsDetailsBinding? = null
    private val binding get() = _binding
    private val args: DetailsFragmentArgs by navArgs()

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

            imgAvatar.setImageResource(user.avatar)
            tvCompany.text = user.company
            tvLocation.text = user.location
            tvUsername.text = user.username
            tvFollowers.text = user.followers.toString()
            tvRepository.text = user.repository.toString()
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
        }
    }

    private fun openInBrowser(user: User) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(user.url))
        startActivity(intent)
    }

    private fun share(user: User) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(
            Intent.EXTRA_TEXT,
            getString(R.string.share_message, user.name, user.url)
        )
        intent.type = "text/plain"
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}