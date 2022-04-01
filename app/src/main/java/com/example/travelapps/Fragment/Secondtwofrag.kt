package com.example.travelapps.Fragment

import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SearchView
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
        val item = menu?.findItem(R.id.search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val newList = ArrayList<Destinasi>()
                for (destinasi in list) {
                    if (destinasi.name.toLowerCase().contains(newText.toLowerCase())) {
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