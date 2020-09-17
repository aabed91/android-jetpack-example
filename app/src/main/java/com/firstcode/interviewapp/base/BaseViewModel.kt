package com.firstcode.interviewapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(){

    private var _onBack = MutableLiveData<Boolean>()
    val onBack : LiveData<Boolean>
        get() = _onBack

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String>
        get() = _errorMessage

    private var _showProgress = MutableLiveData<Boolean>()
    val showProgress : LiveData<Boolean>
        get() = _showProgress

    private var _showFailureDialog = MutableLiveData<Boolean>()
    val showFailureDialog : LiveData<Boolean>
        get() = _showFailureDialog

    private var _done = MutableLiveData<Boolean>()
    val done : LiveData<Boolean>
        get() = _done


    private var _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle : LiveData<String> = _toolbarTitle


    private var _hideKeyboard = MutableLiveData<Boolean>()
    val hideKeyboard : LiveData<Boolean> = _hideKeyboard

    init {
        _showProgress.value = false
    }

    fun onBackPressed(){
        _onBack.value = true
    }

    fun setErrorMessage(message: String){
        _errorMessage.value = message
    }

    fun showErrorMessage(message: String){
        _errorMessage.value = message
    }

    fun showFailureDialog(){
        _showFailureDialog.value = true
    }

    fun showProgress(){
        _showProgress.value = true
    }

    fun hideProgress(){
        _showProgress.value = false
    }

    fun resetOnBack(){
        _onBack.value = false
    }

    fun resetErrorMessage(){
        _errorMessage.value = ""
    }

    fun resetFailureDialog(){
        _showFailureDialog.value = false
    }

    fun done(){
        _done.value = true
    }

    fun resetDone(){
        _done.value = false
    }

    fun setToolbarTitle(title: String){
        _toolbarTitle.value = title
    }

    fun hideKeyboard(){
        _hideKeyboard.value = true
    }

    fun resetHideKeyboard(){
        _hideKeyboard.value = false
    }


}