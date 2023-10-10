package com.example.mbdwaterways

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RectangularResults : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_rectangular_results)

        val sharedPreferences = getSharedPreferences("rectangular_data", Context.MODE_PRIVATE)
        val rect_y_plain = findViewById<TextView>(R.id.h_static)
        val rect_b_plain = findViewById<TextView>(R.id.b_static)

        rect_y_plain.text = sharedPreferences.getString("rect_y","#")
        rect_b_plain.text = sharedPreferences.getString("rect_b","#")

    }

}