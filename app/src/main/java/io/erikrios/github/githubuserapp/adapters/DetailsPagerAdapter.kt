package io.erikrios.github.githubuserapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailsPagerAdapter(
    fragmentActivity: FragmentActivity,
    val fragments: List<Fragment>,
    private val usernameArgKey: String,
    private val username: String
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment = fragments[position].apply {
        arguments = Bundle().apply { putString(usernameArgKey, username) }
    }

    override fun getItemCount(): Int = fragments.size
}