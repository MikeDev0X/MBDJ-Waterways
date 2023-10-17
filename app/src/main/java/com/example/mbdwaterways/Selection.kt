package com.example.mbdwaterways

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class Selection : AppCompatActivity() {

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        val sectionTypes = findViewById<Spinner>(R.id.section_types_spinner)

        val types = arrayOf("Tipo de sección","Rectangular", "Trapezoidal", "Triangular", "Circular", "Parabólico")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)


        val currentActivity = this@Selection
        sectionTypes.adapter = arrayAdapter

        val canalFragments =Canal_Fragments().canalsDic

        sectionTypes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                Log.e("selected", (sectionTypes.selectedItem.toString() == "Rectangular").toString())
                if(sectionTypes.selectedItem.toString() != "Tipo de sección"){
                    canalFragments[sectionTypes.selectedItem.toString()]?.let { loadFragment(it) }
                }
                else{
                    supportFragmentManager.apply {
                        canalFragments[sectionTypes.selectedItem.toString()]?.let { beginTransaction().remove(it).commit() }
                        popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }




}