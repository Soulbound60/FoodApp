package com.example.foodapp.Helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// BasketAdapter


class BasketAdapter(private val context: Context, private val itemsList: List<ItemsModel>) :
    RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewName2)
        val priceTextView: TextView = view.findViewById(R.id.textViewPrice2)
        val deleteBtn: ImageButton = view.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.basket_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price.toString()


        holder.deleteBtn.setOnClickListener {
            deleteItemFromSharedPreferences(item, position)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    private fun deleteItemFromSharedPreferences(item: ItemsModel) {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Retrieve the existing list of items
        val existingItemsJson = sharedPreferences.getString("itemsList", null)
        val gson = Gson()
        val type = object : TypeToken<MutableList<ItemsModel>>() {}.type
        val existingItems: MutableList<ItemsModel> = if (existingItemsJson != null) {
            gson.fromJson(existingItemsJson, type)
        } else {
            mutableListOf()
        }

        // Remove the item from the list
        existingItems.remove(item)

        // Save the updated list of items
        val updatedItemsJson = gson.toJson(existingItems)
        editor.putString("itemsList", updatedItemsJson)
        editor.apply()
    }
}
