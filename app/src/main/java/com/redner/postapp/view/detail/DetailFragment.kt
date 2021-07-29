package com.redner.postapp.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.redner.postapp.R
import com.redner.postapp.databinding.DetailPostBinding

class DetailFragment : Fragment(R.layout.detail_post) {
    private lateinit var binding: DetailPostBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailPostBinding.bind(view)

        viewModel.postLiveData.observe(viewLifecycleOwner) { post ->
            with(binding) {
                detailTitleTextView.text = post.title
                detailBodyTextView.text = post.body
                detailUserTextView.text = getString(R.string.post_by_user, post.userId)
            }
        }
    }
}