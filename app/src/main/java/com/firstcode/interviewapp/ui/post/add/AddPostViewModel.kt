package com.firstcode.interviewapp.ui.post.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstcode.interviewapp.api.RetrofitUtil
import com.firstcode.interviewapp.base.BaseViewModel
import com.firstcode.interviewapp.db.DBPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostViewModel : BaseViewModel() {

    var isEdit = false

    var post = MutableLiveData<DBPost>()

    var title = MutableLiveData<String>()
    var body = MutableLiveData<String>()

    fun setPostData(dbPost: DBPost){
        post.value = dbPost
        title.value = post.value!!.title
        body.value = post.value!!.body
    }

    init {
        setToolbarTitle("Post")
    }

    fun save(){
        hideKeyboard()
        if(title.value.isNullOrBlank()){
            setErrorMessage("Enter post title")
            hideKeyboard()
            return
        }

        if(body.value.isNullOrBlank()){
            setErrorMessage("Enter post body")
            hideKeyboard()
            return
        }

        if(isEdit){
            editPost()
        }else{
            addNewPost()
        }
    }

    private fun addNewPost(){
        showProgress()
        RetrofitUtil.getServiceClass().addPost(title.value!!,body.value!!,1).enqueue(object:
            Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                hideProgress()
                if(response.isSuccessful){
                    done()
                }else{
                    setErrorMessage(response.message())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                hideProgress()
                showFailureDialog()
            }

        })
    }

    private fun editPost(){
        showProgress()
        RetrofitUtil.getServiceClass().updatePost(post.value!!.id,title = title.value!!,body = body.value!!).enqueue(object:
            Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                hideProgress()
                if(response.isSuccessful){
                    done()
                }else{
                    setErrorMessage(response.message())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                hideProgress()
                showFailureDialog()
            }

        })
    }
}