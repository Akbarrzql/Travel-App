package com.example.travelapps.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewAnimator
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.travelapps.Data.Destinasi
import com.example.travelapps.Detail.DetailView
import com.example.travelapps.R


class CardViewDestinasiAdapter : RecyclerView.Adapter<CardViewDestinasiAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        var materi: TextView = itemView.findViewById(R.id.tv_item_name)
//        var materiDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
    }

    class CardViewDestinasiAdapter(private val listDestinasi: ArrayList<Destinasi>) : RecyclerView.Adapter<CardViewDestinasiAdapter.CardViewViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
            val view: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
            return CardViewViewHolder(view)
        }

        override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {

            holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim)


            val destinasi = listDestinasi[position]

            Glide.with(holder.itemView.context)
                .load(destinasi.photo)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)

            holder.tvName.text = destinasi.name
            holder.tvDetail.text = destinasi.detail
            holder.itemView.setOnClickListener(View.OnClickListener {
                val activity = holder.itemView.context as Activity
                val dataView = Intent(activity, DetailView::class.java)
                dataView.putExtra(DetailView.photo, destinasi.photo)
                dataView.putExtra(DetailView.nama, destinasi.name)
                dataView.putExtra(DetailView.detinasidetail, destinasi.detail)
                activity.startActivity(dataView)
            })

        }



        override fun getItemCount(): Int {
            return listDestinasi.size
        }

        inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
            var tvName: TextView = itemView.findViewById(R.id.item_name)
            var tvDetail: TextView = itemView.findViewById(R.id.item_detail)
        }

    }
}
