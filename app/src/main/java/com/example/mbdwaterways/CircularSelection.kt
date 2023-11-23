package com.example.mbdwaterways

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class CircularSelection : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.circular_selection,container, false)

        val reviewButton = view.findViewById<Button>(R.id.review_button)
        val designButton = view.findViewById<Button>(R.id.design_button)

        reviewButton.setOnClickListener{
            val intent = Intent(context,CircularRevision::class.java)
            startActivity(intent)
        }

        designButton.setOnClickListener {
            val intent = Intent(context, CircularDesign::class.java)
            startActivity(intent)
        }

        return view
    }


}