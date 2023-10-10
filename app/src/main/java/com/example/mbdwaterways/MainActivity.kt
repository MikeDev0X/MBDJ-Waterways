package com.example.mbdwaterways

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val oneSectionCanal = findViewById<Button>(R.id.one_section)
        val twoSectionCanal = findViewById<Button>(R.id.two_section)

        oneSectionCanal.setOnClickListener{
            val intent = Intent(this,Selection::class.java)
            startActivity(intent)
        }

    }
}