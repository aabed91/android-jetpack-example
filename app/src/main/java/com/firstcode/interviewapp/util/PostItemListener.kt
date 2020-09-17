package com.firstcode.interviewapp.util

interface PostItemListener {
    fun onItemClicked(position: Int, action: Int)

    companion object{
        const val POST_CLICK = 1
        const val POST_EDIT = 2
        const val POST_DELETE = 3
    }
}