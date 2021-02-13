package com.erikriosetiawan.favoriteusersconsumerapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.erikriosetiawan.favoriteusersconsumerapp.databinding.ItemUserBinding
import com.erikriosetiawan.favoriteusersconsumerapp.models.User

class UserAdapter(private val context: Context, private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var onItemClickListener: ((User) -> Unit)? = null

    fun setOnItemClickListener(listener: (User) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemUserBinding.inflate(layoutInflater)
        binding.clItemUser.apply {
            this.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], onItemClickListener)
    }

    override fun getItemCount(): Int = users.size

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, clickListener: ((User) -> Unit)?) {
            binding.apply {
                Glide.with(itemView).load(user.avatarUrl).into(imgAvatar)
                tvName.text = user.username
                tvUrl.text = user.htmlUrl
                tvType.text = user.type
                tvId.text = user.id.toString()
            }

            itemView.setOnClickListener { clickListener?.let { it(user) } }
        }
    }
}