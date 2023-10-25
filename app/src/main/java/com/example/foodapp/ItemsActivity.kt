package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Helpers.ItemsModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemsActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val itemsKey = "itemsKey"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        sharedPreferences = getSharedPreferences("itemsPrefs", Context.MODE_PRIVATE)

        var basketBtn = findViewById<Button>(R.id.goToBasket)
        basketBtn.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
        // Sample data for the RecyclerView
        val listOfFruits = listOf(
            ItemsModel("Banana", 9, "1 kilo"),
            ItemsModel("Apple", 10, "1 kilo"),
            ItemsModel("Mango", 11, "1 kilo"),
            ItemsModel("Grape", 12, "1 kilo"),
            ItemsModel("Kiwi", 13, "1 kilo"),
        )
        var bananaBtn = findViewById<Button>(R.id.addBanana)
        var appleBtn = findViewById<Button>(R.id.addApple)
        var mangoBtn = findViewById<Button>(R.id.addMango)
        var grapeBtn = findViewById<Button>(R.id.addGrape)
        var kiwiBtn = findViewById<Button>(R.id.addKiwi)

        bananaBtn.setOnClickListener { addItem(listOfFruits[0]) }
        appleBtn.setOnClickListener { addItem(listOfFruits[1]) }
        mangoBtn.setOnClickListener { addItem(listOfFruits[2]) }
        grapeBtn.setOnClickListener { addItem(listOfFruits[3]) }
        kiwiBtn.setOnClickListener { addItem(listOfFruits[4]) }
    }

    private fun addItem(item: ItemsModel) {
        val items = getItems()
        items.add(item)
        saveItems(items)
    }

    private fun saveItems(items: MutableList<ItemsModel>) {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(items)
        editor.putString(itemsKey, json)
        editor.apply()
    }

    private fun getItems(): MutableList<ItemsModel> {
        val json = sharedPreferences.getString(itemsKey, null)
        return if (json != null) {
            val type = object : TypeToken<MutableList<ItemsModel>>() {}.type
            Gson().fromJson(json, type)
        } else {
            mutableListOf()
        }
    }
}
