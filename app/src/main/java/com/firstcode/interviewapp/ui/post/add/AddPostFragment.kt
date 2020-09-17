package com.firstcode.interviewapp.ui.post.add

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.firstcode.interviewapp.R
import com.firstcode.interviewapp.base.BaseFragment
import com.firstcode.interviewapp.databinding.AddPostFragmentBinding
import com.firstcode.interviewapp.util.DialogUtils

class AddPostFragment : BaseFragment<AddPostViewModel, AddPostFragmentBinding>(AddPostViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.add_post_fragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.lifecycleOwner = this

        mBinding.viewModel = viewModel

        val isEdit = AddPostFragmentArgs.fromBundle(requireArguments()).post != null

        viewModel.isEdit = isEdit

        if(isEdit)
            viewModel.setPostData(AddPostFragmentArgs.fromBundle(requireArguments()).post!!)


        viewModel.done.observe(viewLifecycleOwner, Observer {
            if(it){
                DialogUtils.showMessageDialog(requireActivity(),getString(R.string.operation_done)
                ) { dialog, _ ->
                    dialog.dismiss()
                    findNavController().navigateUp()
                }
                viewModel.resetDone()
            }
        })
    }

}