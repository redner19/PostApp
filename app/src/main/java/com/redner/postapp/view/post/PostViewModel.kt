package com.redner.postapp.view.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.redner.postapp.data.model.Post
import com.redner.postapp.data.repository.PostRepository
import com.redner.postapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val repository: PostRepository
) : BaseViewModel() {
    private val _postMutableLiveData = MutableLiveData<List<Post>>()
    val postLiveData: LiveData<List<Post>> = _postMutableLiveData

    init { loadPost() }

    fun loadPost() = viewModelScope.launch {
        launchWithErrorHandling {
            _postMutableLiveData.value = repository.getPosts()
        }
    }
}