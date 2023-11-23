package com.example.mbdwaterways

import android.app.Application
import androidx.fragment.app.Fragment

class Canal_Fragments : Application (){

    val RectangularFillFragment = RectangularFillFragment()
    val CircularSelection = CircularSelection()
    val TriangularFillFragment = TriangularFillFragment()
    val CombinedTriangularRectangularFillFragment = CombinedTriangularRectangularFillFragment()
    val CombinedThreeTrapezoidalFillFragment = CombinedThreeTrapezoidalFillFragment()
    val TrapezoidalFillFragment = TrapezoidalFillFragment()
    val ParabolicFillFragment = ParabolicFillFragment()


    val canalsDic: Map<String, Fragment> = mapOf("Rectangular" to RectangularFillFragment, "Circular" to CircularSelection, "Triangular" to TriangularFillFragment, "Trapezoidal" to TrapezoidalFillFragment, "Parabólica" to ParabolicFillFragment)
    val combinedCanalsDic : Map< String, Fragment> = mapOf("Triangular & Rectangular" to CombinedTriangularRectangularFillFragment, "3 Secciones Trapezoidales" to CombinedThreeTrapezoidalFillFragment)

}