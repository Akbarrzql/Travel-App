package com.example.travelapps.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapps.Adapter.UnsplashAdapter
import com.example.travelapps.Data.News
import com.example.travelapps.Data.NewsData
import com.example.travelapps.R
import com.example.travelapps.Retrofit.ApiConfig
import com.example.travelapps.Retrofit.ResponseUnsplash
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class thirdFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)


        val unsplash = view.findViewById<RecyclerView>(R.id.rv_news)

        ApiConfig.getService().getData().enqueue(object : Callback<ResponseUnsplash>{
            override fun onResponse(call: Call<ResponseUnsplash>, response: Response<ResponseUnsplash>) {
                if (response.isSuccessful){
                    val responseUnsplash = response.body()
                    val dataUnsplash = responseUnsplash?.results
                    val unsplashAdapter = UnsplashAdapter(dataUnsplash)
                    unsplash.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        unsplashAdapter.notifyDataSetChanged()
                        adapter = unsplashAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUnsplash>, t: Throwable) {
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }


}