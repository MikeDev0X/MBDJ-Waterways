package com.example.mbdwaterways

import android.app.Application
import androidx.fragment.app.Fragment

class Canal_Fragments : Application (){
    val RectangularFillFragment = RectangularFillFragment()


    val canalsDic: Map<String, Fragment> = mapOf("Rectangular" to RectangularFillFragment)
}