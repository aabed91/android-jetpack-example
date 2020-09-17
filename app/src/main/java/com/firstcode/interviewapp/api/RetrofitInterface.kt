package com.firstcode.interviewapp.api

import com.firstcode.interviewapp.model.PostsResponse
import retrofit2.Call
import retrofit2.http.*


interface RetrofitInterface {
    @GET("posts")
    suspend fun getPosts() : PostsResponse

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:  Int) : Call<Void>

    @POST("posts")
    @FormUrlEncoded
    fun addPost(@Field("title") title: String, @Field("body") body : String,
            @Field("userId") id : Int) : Call<Void>

    @PATCH("posts/{id}")
    @FormUrlEncoded
    fun updatePost(@Path("id") id: Int,@Field("title") title: String, @Field("body") body : String) : Call<Void>
}