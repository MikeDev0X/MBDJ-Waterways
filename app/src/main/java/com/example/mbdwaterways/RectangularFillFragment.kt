package com.example.mbdwaterways

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class RectangularFillFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.rectangular_fragment,container, false)

        val sharedPreference = context?.getSharedPreferences("rectangular_data", Context.MODE_PRIVATE)

        val rect_h = view.findViewById<EditText>(R.id.h_dynamic)
        val rect_b = view.findViewById<EditText>(R.id.b_dynamic)

        val rect_Q = view.findViewById<EditText>(R.id.q_dynamic)
        val rect_n = view.findViewById<EditText>(R.id.n_dynamic)


        var calculateButton = view.findViewById<Button>(R.id.calculate_button)

        calculateButton?.setOnClickListener{

            Log.e("calculate", "calculate")

            if(rect_h.text.toString() != "" && rect_b.text.toString() != ""){
                val intent = Intent(context,RectangularResults::class.java)

                intent.putExtra("rect_h",rect_h.text.toString())
                intent.putExtra("rect_b",rect_b.text.toString())
                intent.putExtra("rect_Q", rect_Q.text.toString())
                intent.putExtra("rect_n", rect_n.text.toString())

                startActivity(intent)
            }
        }


        return view;
    }
}
