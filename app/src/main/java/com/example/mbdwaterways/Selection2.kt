package com.example.mbdwaterways

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Selection2 : AppCompatActivity() {

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_selection2)

        val combinedSections = findViewById<Spinner>(R.id.combined_sections_spinner)
        val types = arrayOf("Tipo de sección combinada", "Triangular & Rectangular", "3 Secciones Trapezoidales")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        combinedSections.adapter = arrayAdapter

        val canalFragments = Canal_Fragments().combinedCanalsDic

        combinedSections.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(combinedSections.selectedItem.toString() != "Tipo de sección combinada"){
                    canalFragments[combinedSections.selectedItem.toString()]?.let { loadFragment(it) }
                }
                else{
                    supportFragmentManager.apply {
                        canalFragments[combinedSections.selectedItem.toString()]?.let { beginTransaction().remove(it).commit() }
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