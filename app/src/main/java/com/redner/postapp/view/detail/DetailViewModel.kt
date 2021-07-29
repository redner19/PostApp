package com.redner.postapp.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.redner.postapp.data.model.Post

class DetailViewModel constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _postMutableLiveData = MutableLiveData<Post>()
    val postLiveData : LiveData<Post> = _postMutableLiveData

    init {
        savedStateHandle.get<Post>("post")?.let {
            _postMutableLiveData.value = it
        }
    }


}