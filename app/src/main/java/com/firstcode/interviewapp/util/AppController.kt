package com.firstcode.interviewapp.util

import android.app.Application
import android.content.Context


class AppController : Application(){

    init {
        instance = this
    }

    companion object {
        private var instance: AppController? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()

    }

}