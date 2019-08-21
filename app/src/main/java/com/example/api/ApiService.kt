package com.example.api

import com.example.classes.Persona
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(value = "api/?ext&amount=25&region=colombia&gender=random&source=uinames.com")
    fun getAll(): Call<List<Persona>>
}