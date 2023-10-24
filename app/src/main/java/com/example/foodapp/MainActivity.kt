package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.foodapp.Helpers.ConstantEmail
import com.example.foodapp.Helpers.ConstantName
import com.example.foodapp.Helpers.ConstantPassword

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrieveValue()
        val emailText = findViewById<EditText>(R.id.emailTXT)
        val nameText = findViewById<EditText>(R.id.nameTxt)
        val passwordTxt = findViewById<EditText>(R.id.passwordTXT)
        val signUpButton = findViewById<Button>(R.id.buttonSignUp)


        signUpButton.setOnClickListener { if (emailText.text.isNotEmpty() && nameText.text.isNotEmpty() && passwordTxt.text.isNotEmpty()){
            // save values in safe prefrence function
            savePrefrence(emailText.text.toString(), nameText.text.toString(),passwordTxt.text.toString())

        } }

        retrieveValue()
    }


    fun savePrefrence(email:String,name:String,password:String){
        Log.d("TEST!","Save Function")
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        // Save email and password
        myEdit.putString("email", email)
        myEdit.putString("name", name)
        myEdit.putString("password", password)
        myEdit.apply()
        val intent = Intent(this, ItemsActivity::class.java)
        startActivity(intent)
    }
    fun retrieveValue() {
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        // Retrieve email and password
        val email = sharedPreferences.getString("email", null)
        val name = sharedPreferences.getString("name", null)
        val password = sharedPreferences.getString("password", null)

        if (email != null && name != null && password != null) {
            // User has logged in before, start ItemsActivity
            ConstantName = name
            ConstantEmail = email
            ConstantPassword = password
            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }

}