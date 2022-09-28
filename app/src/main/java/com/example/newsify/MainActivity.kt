package com.example.newsify

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: NewsAdapter
    private var articles= mutableListOf<Article>()
    private var pageno=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myAdapter= NewsAdapter(this@MainActivity,articles)
        newsList.adapter=myAdapter
        newsList.layoutManager=LinearLayoutManager(this@MainActivity)
        fun RecyclerView.addOnScrolledToEnd(onScrolledToEnd: () -> Unit){

            this.addOnScrollListener(object: RecyclerView.OnScrollListener(){

                private var loading = true
                private var previousTotal = 0

                override fun onScrollStateChanged(recyclerView: RecyclerView,
                                                  newState: Int) {

                    with(layoutManager as LinearLayoutManager){

                        val visibleItemCount = childCount
                        val totalItemCount = itemCount
                        val firstVisibleItem = findFirstVisibleItemPosition()

                        if (loading && totalItemCount > previousTotal){

                            loading = false
                            previousTotal = totalItemCount
                        }

                        if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + 5)){

                            onScrolledToEnd()
                            loading = true
                        }
                    }
                }
            })
        }
        newsList.addOnScrolledToEnd {
            pageno++
            getnews()
        }
        getnews()
    }
    private fun getnews() {

        val news:Call<News> =NewsService.newsInstance.GetHeadLine("in",pageno)
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val check=response.body()
                if (check!=null){
                   articles.addAll(check.articles)
                    myAdapter.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
              d("Error","something went wrong")
            }
        })
    }

}




