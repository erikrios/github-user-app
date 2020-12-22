package io.erikrios.github.githubuserapp.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.erikrios.github.githubuserapp.databinding.ItemUserBinding
import io.erikrios.github.githubuserapp.models.User

class UserAdapter(private val context: Context, private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var onItemClickListener: ((User) -> Unit)? = null

    fun setOnItemClickListener(listener: (User) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.bind(parent)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, position: Int, clickListener: ((User) -> Unit)?) {
            binding.apply {
                imgAvatar.setImageResource(user.avatar)
                tvName.text = user.name
                tvLocation.text = user.location
                tvUsername.text = user.username
                tvFollowers.text = user.followers.toString()
            }

            itemView.setOnClickListener { clickListener?.let { it(user) } }
        }
    }
}