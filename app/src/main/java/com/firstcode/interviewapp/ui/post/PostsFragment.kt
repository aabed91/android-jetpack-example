package com.firstcode.interviewapp.ui.post

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.firstcode.interviewapp.R
import com.firstcode.interviewapp.base.BaseFragment
import com.firstcode.interviewapp.databinding.PostsFragmentBinding
import com.firstcode.interviewapp.model.Post
import com.firstcode.interviewapp.util.PostItemListener
import com.firstcode.interviewapp.util.isInternetConnectionAvailable
import com.firstcode.interviewapp.util.showMessageDialog

class PostsFragment : BaseFragment<PostsViewModel, PostsFragmentBinding>(PostsViewModel::class.java) {

    override fun getLayoutRes(): Int {
        return R.layout.posts_fragment
    }

    private lateinit var postsAdapter: PostsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.lifecycleOwner = this

        mBinding.viewModel = viewModel

        postsAdapter = PostsAdapter(object : PostItemListener {
            override fun onItemClicked(position: Int, action: Int) {
                when (action) {
                    PostItemListener.POST_DELETE -> {
                        viewModel.deletePost(postsAdapter.currentList!![position]!!.id)
                    }
                    PostItemListener.POST_CLICK -> {
                        findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(postsAdapter.currentList!![position]!!))
                    }
                    PostItemListener.POST_EDIT ->{
                        if(isInternetConnectionAvailable()) {
                            findNavController().navigate(
                                PostsFragmentDirections.actionPostsFragmentToAddPostFragment(
                                    postsAdapter.currentList!![position]!!
                                )
                            )
                        }else{
                            showMessageDialog("Please check internet connection")
                        }
                    }
                }
            }
        })

        mBinding.postsRecycler.adapter = postsAdapter

        viewModel.posts.observe(viewLifecycleOwner, Observer {
            postsAdapter.submitList(it)
        })


        mBinding.addNewPost.setOnClickListener {
            if(isInternetConnectionAvailable()) {
                findNavController().navigate(
                    PostsFragmentDirections.actionPostsFragmentToAddPostFragment()
                )
            }else{
                showMessageDialog("Please check internet connection")
            }
        }

    }


}