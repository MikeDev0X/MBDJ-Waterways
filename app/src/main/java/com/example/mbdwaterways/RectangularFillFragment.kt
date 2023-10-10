package com.example.mbdwaterways

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class RectangularFillFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.rectangular_fragment,container, false)

        val sharedPreference = context?.getSharedPreferences("rectangular_data", Context.MODE_PRIVATE)
        val rect_y = activity?.findViewById<EditText>(R.id.h_dynamic)
        val rect_b = activity?.findViewById<EditText>(R.id.b_dynamic)

        var calculateButton = activity?.findViewById<Button>(R.id.calculate_button)

        calculateButton?.setOnClickListener{

            val intent = Intent(context,RectangularResults::class.java)
            startActivity(intent)
        }










        rect_y?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
                Log.e("RECT_Y_UPDATED", "rect_y_updated")

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // update shared preferences

                with(sharedPreference?.edit()){
                    this?.putString("rect_y", rect_y.toString())
                    this?.apply()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })

        rect_b?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // update shared preferences

                with(sharedPreference?.edit()){
                    this?.putString("rect_b", rect_b.toString())
                    this?.apply()
                    Toast.makeText(activity, "doneeee", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }

        })

        return view;
    }
}
