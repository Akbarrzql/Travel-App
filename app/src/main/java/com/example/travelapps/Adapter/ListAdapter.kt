package com.example.travelapps.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapps.Data.DB.User
import com.example.travelapps.Data.DB.UserViewModel
import com.example.travelapps.R
import kotlinx.android.synthetic.main.list_item_history.view.*

class ListAdapter: RecyclerView.Adapter<com.example.travelapps.Adapter.ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
    private lateinit var mUserViewModel: UserViewModel

    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.travelapps.Adapter.ListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_history, parent, false))
    }

    override fun onBindViewHolder(holder: com.example.travelapps.Adapter.ListAdapter.MyViewHolder, position: Int) {
        val currentItem =userList[position]
        holder.itemView.tvIdKode.text = currentItem.id.toString()
        holder.itemView.tvKelas.text = currentItem.tipeKelas.toString()
        holder.itemView.tvNama.text = currentItem.firtsLastName
        holder.itemView.tvKeberangkatan.text = currentItem.keberangakatan
        holder.itemView.tvTujuan.text = currentItem.tujuan
        holder.itemView.tvDate.text = currentItem.tanggal
        holder.itemView.tvHp.text = currentItem.telpHp
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}