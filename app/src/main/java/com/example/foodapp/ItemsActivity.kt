package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Helpers.FruitAdapter
import com.example.foodapp.Helpers.ItemsModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        var flotingButoon = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        flotingButoon.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }

        // Sample data for the RecyclerView
        val itemsList = listOf(
            ItemsModel("Item 1", 100, "Description 1"),
            ItemsModel("Item 2", 200, "Description 2"),
            ItemsModel("Item 3", 300, "Description 3")
        )

        // Initialize the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = FruitAdapter(this, itemsList)
        recyclerView.adapter = adapter
    }
}
