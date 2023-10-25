package com.example.foodapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.Helpers.ItemsModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class BasketActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val itemsKey = "itemsKey"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        sharedPreferences = getSharedPreferences("itemsPrefs", Context.MODE_PRIVATE)

        val items = getItems()

        val totalTxt = findViewById<TextView>(R.id.totalTxt)

        // ... (other code)

        updateTotal(items, totalTxt)
        val item1Name = findViewById<TextView>(R.id.item1name)
        val item1Price = findViewById<TextView>(R.id.item1price)
        val item1Delete = findViewById<ImageButton>(R.id.item1delete)

        val item2Name = findViewById<TextView>(R.id.item2name)
        val item2Price = findViewById<TextView>(R.id.item2price)
        val item2Delete = findViewById<ImageButton>(R.id.item2delete)

        val item3Name = findViewById<TextView>(R.id.item3name)
        val item3Price = findViewById<TextView>(R.id.item3price)
        val item3Delete = findViewById<ImageButton>(R.id.item3delete)

        val item4Name = findViewById<TextView>(R.id.item4name)
        val item4Price = findViewById<TextView>(R.id.item4price)
        val item4Delete = findViewById<ImageButton>(R.id.item4delete)

        val item5Name = findViewById<TextView>(R.id.item5name)
        val item5Price = findViewById<TextView>(R.id.item5price)
        val item5Delete = findViewById<ImageButton>(R.id.item5delete)



        // ... similarly for other items

        if (items.size > 0) {
            item1Name.text = items[0].name
            item1Price.text = items[0].price.toString()
            item1Delete.setOnClickListener { deleteItem(0) }
        }

        if (items.size > 1) {
            item2Name.text = items[1].name
            item2Price.text = items[1].price.toString()
            item2Delete.setOnClickListener { deleteItem(1) }
        }
        if (items.size > 2) {
            item3Name.text = items[2].name
            item3Price.text = items[2].price.toString()
            item3Delete.setOnClickListener { deleteItem(2) }
        }
        if (items.size > 3) {
            item4Name.text = items[3].name
            item4Price.text = items[3].price.toString()
            item4Delete.setOnClickListener { deleteItem(3) }
        }
        if (items.size > 4) {
            item5Name.text = items[4].name
            item5Price.text = items[4].price.toString()
            item5Delete.setOnClickListener { deleteItem(4) }
        }

        // ... similarly for other items

        val backToItems = findViewById<FloatingActionButton>(R.id.backToItems)
        backToItems.setOnClickListener {
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
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

    private fun deleteItem(index: Int) {
        val items = getItems()
        if (index < items.size) {
            items.removeAt(index)
            saveItems(items)
            recreate()
        }
    }

    private fun saveItems(items: MutableList<ItemsModel>) {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(items)
        editor.putString(itemsKey, json)
        editor.apply()
    }

    private fun updateTotal(items: MutableList<ItemsModel>, totalTxt: TextView) {
        val total = items.sumOf { it.price }
        totalTxt.text = total.toString()
    }
}