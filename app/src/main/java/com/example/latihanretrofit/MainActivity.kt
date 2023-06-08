package com.example.latihanretrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView

    lateinit var list:ArrayList<AlbumsItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        list= ArrayList()
        val layoutManager = LinearLayoutManager(this)

        val adapter = RecycleAdapter(list,this)
        recyclerView.layoutManager = layoutManager
        val retrofit:Retrofit= Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build()
        val api:ApiInterface=retrofit.create(ApiInterface::class.java)
        val call: Call<Albums> = api.getData()

        call.enqueue(object:Callback<Albums?>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Albums?>, response: Response<Albums?>) {
                if (response.isSuccessful){
                    list.clear()
                    for (myData in response.body()!!)
                        list.add(myData)
                }
                adapter.notifyDataSetChanged()
                recyclerView.adapter=adapter
            }

            override fun onFailure(call: Call<Albums?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
            }

        })
//        api.getAlbums().enqueue(object : Callback<Albums>){
//            override fun onResponse(call: Call<Albums>, response: Response<Albums>)
//        }
    }
}