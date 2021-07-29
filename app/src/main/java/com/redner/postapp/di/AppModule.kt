package com.redner.postapp.di

import com.redner.postapp.data.repository.PostRepository
import com.redner.postapp.data.repository.PostRepositoryImpl
import com.redner.postapp.data.service.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val URL = "https://jsonplaceholder.typicode.com/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit) : PostService =
        retrofit.create(PostService::class.java)

    @Singleton
    @Provides
    fun providesRepository(service: PostService): PostRepository = PostRepositoryImpl(service)
}