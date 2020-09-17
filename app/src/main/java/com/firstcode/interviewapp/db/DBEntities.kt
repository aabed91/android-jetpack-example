package com.firstcode.interviewapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class DBPost constructor(
    var userId: Int = 0,
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var body: String = ""
) : Serializable


