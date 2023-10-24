package com.example.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Helpers.BasketAdapter
import com.example.foodapp.Helpers.FruitAdapter
import com.example.foodapp.Helpers.ItemsModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        var listRetreived = retrieveItemsFromSharedPreferences(this)

        val backBtn = findViewById<FloatingActionButton>(R.id.delFlotin)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = BasketAdapter(this, listRetreived)
        recyclerView.adapter = adapter
        backBtn.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }

    }



    private fun retrieveItemsFromSharedPreferences(context: Context): List<ItemsModel> {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

        // Retrieve the list of items
        val itemsJson = sharedPreferences.getString("itemsList", null)
        if (itemsJson != null) {
            val gson = Gson()
            val type = object : TypeToken<List<ItemsModel>>() {}.type
            return gson.fromJson(itemsJson, type)
        }

        // Return an empty list if no items are found
        return emptyList()
    }

}