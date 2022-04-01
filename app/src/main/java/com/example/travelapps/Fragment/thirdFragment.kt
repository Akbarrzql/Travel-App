package com.example.travelapps.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapps.Adapter.ListNewsAdapter
import com.example.travelapps.Data.News
import com.example.travelapps.Data.NewsData
import com.example.travelapps.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [thirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class thirdFragment : Fragment() {

    private lateinit var rvnews:RecyclerView
    private var list: ArrayList<News> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvnews = view.findViewById(R.id.rv_news)
        rvnews.setHasFixedSize(true)

        list.addAll(NewsData.listNews)
        showRecyclerList()


    }

    private fun showRecyclerList() {
        rvnews.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        val listNewsAdapter = ListNewsAdapter.ListNewsAdapter(list)
        rvnews.adapter = listNewsAdapter

//        rvnews.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))

        // make the adapter the data set
        // changed for the recycler view
        listNewsAdapter.notifyDataSetChanged()
    }
}