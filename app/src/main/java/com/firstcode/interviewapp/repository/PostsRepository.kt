package com.firstcode.interviewapp.repository

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import com.firstcode.interviewapp.api.RetrofitUtil
import com.firstcode.interviewapp.db.DBPost
import com.firstcode.interviewapp.db.PostsDatabase
import com.firstcode.interviewapp.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepository (private val database: PostsDatabase){

    val factory: DataSource.Factory<Int, DBPost> =  database.postDao.getPostsPaged()
    val pagedListBuilder: LivePagedListBuilder<Int, DBPost>  = LivePagedListBuilder(factory,10)

    val postsList = pagedListBuilder.build()

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val posts = RetrofitUtil.getServiceClass().getPosts()
            database.postDao.insertAll(posts.asDatabaseModel())
        }
    }
}