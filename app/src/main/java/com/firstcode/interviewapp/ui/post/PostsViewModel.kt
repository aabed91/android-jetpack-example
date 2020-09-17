package com.firstcode.interviewapp.ui.post

import android.app.Notification
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.firstcode.interviewapp.api.RetrofitUtil
import com.firstcode.interviewapp.base.BaseViewModel
import com.firstcode.interviewapp.db.DBPost
import com.firstcode.interviewapp.db.getDatabase
import com.firstcode.interviewapp.model.Post
import com.firstcode.interviewapp.repository.PostsRepository
import com.firstcode.interviewapp.util.AppController
import com.firstcode.interviewapp.util.isInternetConnectionAvailable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PostsViewModel : BaseViewModel() {


    private val postsRepository = PostsRepository(getDatabase())

    val posts = postsRepository.postsList



    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                postsRepository.refreshVideos()

            } catch (networkError: IOException) {
                println(networkError.message)
            }
        }
    }


    fun deletePost(id: Int){
        if(isInternetConnectionAvailable()) {
            RetrofitUtil.getServiceClass().deletePost(id).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    println("Delete done $response")
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t.message)
                }

            })
        }else{
            showErrorMessage("Please check internet connection")
        }
    }
}