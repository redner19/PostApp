package com.redner.postapp.view.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redner.postapp.data.model.Post
import com.redner.postapp.databinding.ItemPostBinding

class PostAdapter(private val listener: OnPostItemSelected) :
    ListAdapter<Post, PostAdapter.ViewHolder>(DIFF_UTIL) {

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION)
                        listener.onItemSelected(getItem(position))
                }
            }
        }

        fun bind(post: Post) {
            with(binding) {
                postTitleTextView.text = post.title
                postBodyTextView.text = post.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post)
            = oldItem == newItem

            override fun areContentsTheSame(oldItem: Post, newItem: Post)
            = oldItem.id == newItem.id
        }
    }
}