package com.redner.postapp.data.repository

import com.redner.postapp.data.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
}