package com.redner.postapp.data.repository

import com.redner.postapp.data.dto.transform.transform
import com.redner.postapp.data.model.Post
import com.redner.postapp.data.service.PostService

class PostRepositoryImpl constructor(private val service: PostService) : PostRepository {
    override suspend fun getPosts(): List<Post> =
        service.getPosts().map { it.transform() }
}