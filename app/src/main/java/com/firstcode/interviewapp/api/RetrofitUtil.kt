package com.firstcode.interviewapp.api


class RetrofitUtil {
    companion object{
        fun getServiceClass() : RetrofitInterface {
            return RetrofitApi.getRetrofit("https://jsonplaceholder.typicode.com/").create(
                RetrofitInterface::class.java)
        }
    }
}