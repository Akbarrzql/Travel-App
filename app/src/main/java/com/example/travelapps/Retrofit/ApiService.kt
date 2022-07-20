package com.example.travelapps.Retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("search/photos?page=1&query=indonesia&client_id=Bzs6918UHXvbfwftwpa7SXclHEUKSgbtye08RZa5FIs")
    fun getData(): Call<ResponseUnsplash>
}