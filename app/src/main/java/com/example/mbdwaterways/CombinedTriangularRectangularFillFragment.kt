package com.example.mbdwaterways

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CombinedTriangularRectangularFillFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = layoutInflater.inflate(R.layout.combined_triangular_rectangular,container, false)

        return view

    }
}