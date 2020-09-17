package com.firstcode.interviewapp.ui.post.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstcode.interviewapp.R
import com.firstcode.interviewapp.base.BaseFragment
import com.firstcode.interviewapp.databinding.PostDetailsFragmentBinding

class PostDetailsFragment : BaseFragment<PostDetailsViewModel, PostDetailsFragmentBinding>(PostDetailsViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.post_details_fragment
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.lifecycleOwner = this

        mBinding.viewModel = viewModel


        mBinding.post = PostDetailsFragmentArgs.fromBundle(requireArguments()).post
    }

}