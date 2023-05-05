package com.example.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.Article
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AllNewsAdapter(val context: Context): RecyclerView.Adapter<AllNewsAdapter.ArticleViewHolder>(){

    var articles: ArrayList<Article> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.single_all_news,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article:Article = articles[position]
        holder.title.text = article.title

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(article.publishedAt)
        val formattedDate = outputFormat.format(date)

        holder.date.text = formattedDate

        if(article.author != null){
            holder.author.text = article.author.toString()
        }else{
            holder.author.text = "-"
        }

        Glide.with(context)
            .load(article.urlToImage)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.imageView_singleAllNews)
        var title = itemView.findViewById<TextView>(R.id.title_singleAllNews)
        val author = itemView.findViewById<TextView>(R.id.author_singleAllNews)
        val date = itemView.findViewById<TextView>(R.id.date_singleAllNews)
    }

    fun clear() {
        articles.clear()
        notifyDataSetChanged()
    }

    fun addAll(art: List<Article>) {
        articles.addAll(art)
        notifyDataSetChanged()
    }

}