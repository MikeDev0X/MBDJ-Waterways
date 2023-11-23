package com.example.mbdwaterways

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
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

        val sections1 = findViewById<RadioButton>(R.id.sections_1)
        val sections2 = findViewById<RadioButton>(R.id.sections_2)



        val types = arrayOf("Tipo de sección","Rectangular", "Triangular")
        val types2 = arrayOf("Tipo de sección", "Circular", "Parabólica", "Trapezoidal")


        sections1.setOnCheckedChangeListener { button, b ->
            if(b){
                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
                sectionTypes.adapter = arrayAdapter
            }
        }

        sections2.setOnCheckedChangeListener { button, b ->
            if(b){
                val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types2)
                sectionTypes.adapter = arrayAdapter
            }
        }


        val canalFragments =Canal_Fragments().canalsDic

        sectionTypes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

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