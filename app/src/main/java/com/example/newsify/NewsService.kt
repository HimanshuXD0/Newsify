package com.example.newsify

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL= "https://newsapi.org/"
const val API_KEY= "33b70bc15b9f4d05807468495af79e5c"
interface NewsInterface{
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun GetHeadLine(@Query("Country")Country:String, @Query("Page")Page: Int) : Call<News>
}

// THIS IS LIKE A INDIVIDUAL SINGLETON CLASS IN JAVA BUT GOT IT //

object NewsService{
    val newsInstance : NewsInterface
    init {
        val retrofit= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        newsInstance =retrofit.create(NewsInterface::class.java)

    }
}