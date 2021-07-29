package com.redner.postapp.view.post.adapter

import com.redner.postapp.data.model.Post

interface OnPostItemSelected {
    fun onItemSelected(post: Post)
}