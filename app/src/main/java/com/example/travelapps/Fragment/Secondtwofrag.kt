package com.example.travelapps.Fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapps.Adapter.CardViewDestinasiAdapter
import com.example.travelapps.Data.Destinasi
import com.example.travelapps.Data.DestinasiData
import com.example.travelapps.R
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import java.util.*
import kotlin.collections.ArrayList


class secondtwofrag : Fragment() {

    private lateinit var listDestinasi: RecyclerView
    private lateinit var cardViewDestinasiAdapter: CardViewDestinasiAdapter
    private var list: ArrayList<Destinasi> = arrayListOf()

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        list_Destinasi.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    extendedfab.extend()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && extendedfab.isExtended()) {
                    extendedfab.shrink()
                }
            }
        })




        listDestinasi = view.findViewById(R.id.list_Destinasi)
        listDestinasi.setHasFixedSize(true)

        list.addAll(DestinasiData.listdestinasi)
        showRecyclerCardView()



    }


    private fun showRecyclerCardView() {
        listDestinasi.layoutManager = LinearLayoutManager(this.context)
        val cardViewDestinasiAdapter = CardViewDestinasiAdapter.CardViewDestinasiAdapter(list)
        listDestinasi.adapter = cardViewDestinasiAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_one, menu)
        val item = menu.findItem(R.id.search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val newList = ArrayList<Destinasi>()
                for (destinasi in list) {
                    if (destinasi.name.toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))) {
                        newList.add(destinasi)
                    }
                }
                listDestinasi.adapter = CardViewDestinasiAdapter.CardViewDestinasiAdapter(newList)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.search -> {
                true
            }
        }
    }
}