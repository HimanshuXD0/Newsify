package com.example.newsify

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context, private val articles: List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       val inflater:LayoutInflater=LayoutInflater.from(parent.context)
        val view : View= inflater.inflate(R.layout.item_layout,parent,false)
      return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articles: Article =articles[position]
        holder.newsTitle.text= articles.title
        holder.newsDiscription.text= articles.description
        Glide.with(context).load(articles.urlToImage).into(holder.newsImage)
         holder.itemView.setOnClickListener{
             val intent = Intent(context,DetailActivity::class.java)
             intent.putExtra("URL",articles.url)
             context.startActivity(intent)
         }

    }

    override fun getItemCount(): Int {
      return articles.size
    }

    class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var newsImage: ImageView= itemView.findViewById(R.id.newsImage)
        var newsTitle: TextView= itemView.findViewById(R.id.newsTitle)
        var newsDiscription: TextView= itemView.findViewById(R.id.newsDiscription)
    }
}
