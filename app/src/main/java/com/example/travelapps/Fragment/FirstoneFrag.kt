package com.example.travelapps.Fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.travelapps.Adapter.SliderAdapter
import com.example.travelapps.Main.*
import com.example.travelapps.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_first.*
import kotlin.math.abs


class firstoneFrag : Fragment() {

    private lateinit var viewPager2: ViewPager2
    private val slideHandler = Handler()



    override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)


        viewPager2 = view.findViewById(R.id.viewPager_ImageSlider)

        val slideritem: MutableList<Slideritem> = ArrayList()
        slideritem.add(Slideritem(R.drawable.borobudur1))
        slideritem.add(Slideritem(R.drawable.bromo1))
        slideritem.add(Slideritem(R.drawable.kotasemarang))
        slideritem.add(Slideritem(R.drawable.kawahijen1))
        slideritem.add(Slideritem(R.drawable.nusattimur))

        viewPager2.adapter = SliderAdapter(slideritem, viewPager2)

        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTranformer = CompositePageTransformer()
        compositePageTranformer.addTransformer(MarginPageTransformer(30))
        compositePageTranformer.addTransformer { page, position -> val r = 1 - abs(position)
        page.scaleY = 0.85f + r * 0.25f}

        viewPager2.setPageTransformer(compositePageTranformer)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                slideHandler.removeCallbacks(slideRunnable)
                slideHandler.postDelayed(slideRunnable, 3000)
            }
        })

        (activity as AppCompatActivity).supportActionBar


        cv_Pesawat.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, PemsenanActivity::class.java)
            startActivity(intent)
        })


    }
    private val slideRunnable = Runnable{
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        slideHandler.postDelayed(slideRunnable, 3000)
    }

    override fun onResume() {
        super.onResume()
        slideHandler.postDelayed(slideRunnable, 3000)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
//        inflater.inflate(R.menu.menu_one, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
//            R.id.search -> {
//                true
//            }
            R.id.action_notifikasi -> {
                val intent = Intent(activity, NotificationActivity::class.java)
                startActivity(intent)
            }
            R.id.action_inbox -> {
                val intent = Intent(activity, InboxActivity::class.java)
                startActivity(intent)
            }
            R.id.history -> {
                val intent = Intent(activity, HistoryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

