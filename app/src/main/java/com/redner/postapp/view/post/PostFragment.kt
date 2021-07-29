package com.redner.postapp.view.post

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.redner.postapp.R
import com.redner.postapp.data.model.Post
import com.redner.postapp.databinding.PostBinding
import com.redner.postapp.view.base.BaseViewModel
import com.redner.postapp.view.post.adapter.OnPostItemSelected
import com.redner.postapp.view.post.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PostFragment : Fragment(R.layout.post), OnPostItemSelected {
    private lateinit var binding: PostBinding
    private val viewModel by viewModels<PostViewModel>()
    private val adapter by lazy { PostAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PostBinding.bind(view)

        with(binding) {
            postRecyclerView.adapter = adapter
            postSwipeToRefresh.setOnRefreshListener { viewModel.loadPost() }
        }

        viewModel.postLiveData.observe(viewLifecycleOwner) { posts ->
            binding.postSwipeToRefresh.isRefreshing = false
            adapter.submitList(posts)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.baseModelEventFlow.collect { event ->
                if (event is BaseViewModel.BaseModelEvent.Fail)
                    Snackbar.make(
                        binding.root,
                        getString(event.errorMessage),
                        Snackbar.LENGTH_LONG
                    ).show()
            }
        }
    }

    override fun onItemSelected(post: Post) {
        findNavController().navigate(
            PostFragmentDirections
                .actionPostFragmentToDetailFragment(post)
        )
    }
}