package com.example.newsapp.network

import com.example.newsapp.data.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "718cd85bb6204edda75a6607a64f7574"

interface NewsInterface {

    @GET("v2/top-headlines?country=id&apiKey=$API_KEY&pageSize=5")
    fun getHeadLines(@Query("country") country: String,@Query("page") page : Int) : Call<News>

    @GET("v2/everything?apiKey=$API_KEY")
    fun getAllNews(@Query("q") q: String,@Query("page") page : Int) : Call<News>

}

object NewsService{

    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}