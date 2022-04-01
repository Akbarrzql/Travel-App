package com.example.travelapps.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.travelapps.Data.News
import com.example.travelapps.Data.NewsData
import com.example.travelapps.Detail.DetailNews
import com.example.travelapps.Detail.DetailNews.Companion.photo
import com.example.travelapps.Detail.DetailView
import com.example.travelapps.Detail.DetailView.Companion.photo
import com.example.travelapps.R

class ListNewsAdapter: RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name1)
        var tvDetail : TextView = itemView.findViewById(R.id.tv_item_detail1)
    }

    class ListNewsAdapter(private val listNews: ArrayList<News>) : RecyclerView.Adapter<ListNewsAdapter.ListViewHolder>() {

        var listNewsFilter = ArrayList<News>()

        init {
            listNewsFilter =  listNews
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
            return ListViewHolder(view)
        }


        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {


            holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim2)
            val news = listNews[position]
            Glide.with(holder.itemView.context)
                .load(news.photo)
                .apply(RequestOptions().override(55, 55))
                .into(holder.photo)

            holder.tvName.text = news.name
            holder.tvDetail.text = news.detail
            holder.itemView.setOnClickListener(View.OnClickListener {
                val activity = holder.itemView.context as Activity
                val dataNews = Intent(activity, DetailNews::class.java)
                dataNews.putExtra(DetailNews.photo, news.photo)
                dataNews.putExtra(DetailNews.nama, news.name)
                dataNews.putExtra(DetailNews.newsdetail, news.detail)
                activity.startActivity(dataNews)
            })
        }

        override fun getItemCount(): Int {
            return listNews.size
            return listNewsFilter.size
        }


        inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var photo: ImageView = itemView.findViewById(R.id.img_item_photo1)
            var tvName: TextView = itemView.findViewById(R.id.tv_item_name1)
            var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail1)
        }
    }
}