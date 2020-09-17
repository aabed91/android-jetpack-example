package com.firstcode.interviewapp.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.*
import com.firstcode.interviewapp.util.AppController

@Dao
interface PostDao{
    @Query("select * from dbpost")
    fun getPostsPaged() : DataSource.Factory<Int, DBPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<DBPost>)
}

@Database(entities = [DBPost::class], version = 1)
abstract class PostsDatabase: RoomDatabase() {
    abstract val postDao: PostDao
}

private lateinit var INSTANCE: PostsDatabase

fun getDatabase(): PostsDatabase {
    synchronized(PostsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                AppController.applicationContext(),
                PostsDatabase::class.java,
                "posts"
            ).build()
        }
    }

    return INSTANCE
}