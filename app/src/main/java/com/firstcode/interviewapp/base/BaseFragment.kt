package com.firstcode.interviewapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firstcode.interviewapp.util.hideKeyboard
import com.firstcode.interviewapp.util.showErrorMessageDialog
import com.firstcode.interviewapp.util.showMessageDialog

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : Fragment() {
    lateinit var viewModel: VM
    open lateinit var mBinding: DB
    fun init(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    open fun init() {}
    @LayoutRes
    abstract fun getLayoutRes(): Int

    private fun getViewM(): VM = ViewModelProvider(this).get(mViewModelClass)
    open fun onInject() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewM()


        viewModel.errorMessage.observe(this, Observer {
            if(!it.isNullOrBlank()){
                showMessageDialog(it)
                viewModel.resetErrorMessage()
            }
        })


        viewModel.showFailureDialog.observe(this, Observer {
            if(it){
                showErrorMessageDialog()
                viewModel.resetFailureDialog()
            }
        })

        viewModel.onBack.observe(this, Observer {
            if(it){
                hideKeyboard()
                findNavController().navigateUp()
                viewModel.resetOnBack()
            }
        })

        viewModel.hideKeyboard.observe(this, Observer {
            if(it){
                hideKeyboard()
                viewModel.resetHideKeyboard()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        init(inflater, container!!)
        init()
        super.onCreateView(inflater, container, savedInstanceState)

        return mBinding.root
    }

    open fun refresh() {}
}