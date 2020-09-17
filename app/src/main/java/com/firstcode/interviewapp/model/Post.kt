package com.firstcode.interviewapp.model


import com.firstcode.interviewapp.db.DBPost
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("body")
    var body: String = ""
)

fun List<Post>.asDatabaseModel(): List<DBPost> {
    return map {
        DBPost(
            id = it.id,
            userId = it.userId,
            body = it.body,
            title = it.title)
    }
}