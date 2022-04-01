package com.example.travelapps.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.travelapps.Main.Slideritem
import com.example.travelapps.R
import com.makeramen.roundedimageview.RoundedImageView

class SliderAdapter internal constructor(
    slideritem: MutableList<Slideritem>,
    viewPager2: ViewPager2
):RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){

    private val slideritem: List<Slideritem>
    private val viewPager2: ViewPager2

    init {
        this.slideritem = slideritem
        this.viewPager2 = viewPager2
    }

    class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val imageView: RoundedImageView = itemView.findViewById(R.id.imageSlide)

        fun image(slideritem: Slideritem){
            imageView.setImageResource(slideritem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(slideritem[position] )
        if (position == slideritem.size - 2){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return slideritem.size
    }

    private val runnable = Runnable {
        slideritem.addAll(slideritem)
    }
}