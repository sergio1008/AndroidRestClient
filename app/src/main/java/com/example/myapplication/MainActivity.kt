package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

import com.example.api.ApiService
import com.example.classes.Persona
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl( "https://uinames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private  val  service = retrofit.create<ApiService>(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getPersonasService(view: View){

        service.getAll().enqueue(object:Callback<List<Persona>>{
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                var mensajes = response?.body()
                val personas = findViewById<TextView>(R.id.txtPersonas)
                personas.apply {
                    text= null
                }
                mensajes!!.forEach {
                    var p = personas.text.toString()
                    p = "$p Nombre: ${it.name} ${it.surname} \n"
                    personas.apply {
                        text = p
                    }
                }
            }
            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                print(t.message)
            }
        })
    }

}