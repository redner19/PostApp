package com.redner.postapp.data.service

import com.redner.postapp.data.dto.response.PostListDto
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPosts() : PostListDto
}